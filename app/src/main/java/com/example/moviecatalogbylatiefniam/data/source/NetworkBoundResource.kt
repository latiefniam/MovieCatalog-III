package com.example.moviecatalogbylatiefniam.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.moviecatalogbylatiefniam.data.source.remote.StatusResponse
import com.example.moviecatalogbylatiefniam.data.source.remote.response.ApiResponse
import com.example.moviecatalogbylatiefniam.utils.AppExecutor
import com.example.moviecatalogbylatiefniam.vo.Resource


abstract class NetworkBoundResource<ResultType, RequestType>(
    private val mExectors: AppExecutor) {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)
        @Suppress("LeakingThis")
        val dbSource = loadFromDB()

        result.addSource(dbSource){
            data -> result.removeSource(dbSource)
            if (shouldFecth(data)){
                fetchFormNetwork(dbSource)
            }else{
                result.addSource(dbSource){
                    newData -> result.value = Resource.succes(newData)
                }
            }
        }
    }

    protected open fun onFetchFailed(){}
    protected abstract fun loadFromDB():LiveData<ResultType>
    protected abstract fun createCall():LiveData<ApiResponse<RequestType>>
    protected abstract fun saveCallResult(data: RequestType)
    protected abstract fun shouldFecth(data: ResultType):Boolean
    private fun fetchFormNetwork( dbSource: LiveData<ResultType>){
        val apiResponse = createCall()

        result.addSource(dbSource){
            newData -> result.value = Resource.loading(newData)
        }
        result.addSource(apiResponse){
            response -> result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when(response.status) {
                StatusResponse.SUCCESS ->
                    mExectors.diskIO().execute {
                        saveCallResult(response.body)
                        mExectors.mainThread().execute {
                            result.addSource(loadFromDB()) { newData ->
                                result.value = Resource.succes(newData)
                            }
                        }
                    }
                StatusResponse.EMPTY ->
                    mExectors.mainThread().execute {
                        result.addSource(loadFromDB()) { newData ->
                            result.value = Resource.succes(newData)
                        }
                    }
                StatusResponse.ERROR -> {
                    onFetchFailed()
                    result.addSource(dbSource) { newData ->
                        result.value = Resource.error(response.message, newData)
                    }
                }
            }
        }
    }

    fun asLiveData(): LiveData<Resource<ResultType>> = result
}