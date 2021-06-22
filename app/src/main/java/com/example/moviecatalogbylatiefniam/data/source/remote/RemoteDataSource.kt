package com.example.moviecatalogbylatiefniam.data.source.remote

import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogbylatiefniam.data.source.remote.response.ApiResponse
import com.example.moviecatalogbylatiefniam.data.source.remote.response.MovResponse
import com.example.moviecatalogbylatiefniam.data.source.remote.response.TvResponse
import com.example.moviecatalogbylatiefniam.utils.Espressoldling
import com.example.moviecatalogbylatiefniam.utils.JsonHelper



class RemoteDataSource private constructor(private val jsonHelper: JsonHelper){
    private val handler = android.os.Handler(Looper.getMainLooper())

    companion object{
        private const val SERVICE_LATENCY:Long = 1000

        @Volatile
        private var instance :RemoteDataSource? = null

        fun getInstance(helper: JsonHelper):RemoteDataSource=
            instance?: synchronized(this){
                instance?: RemoteDataSource(helper)
            }
    }

    fun getMovies():LiveData<ApiResponse<List<MovResponse>>>{
        Espressoldling.increment()
        val result = MutableLiveData<ApiResponse<List<MovResponse>>>()
        handler.postDelayed({
            result.value = ApiResponse.success(jsonHelper.loadMovie())
            Espressoldling.decrement()
        }, SERVICE_LATENCY)
        return result
    }

    fun getTvShows():LiveData<ApiResponse<List<TvResponse>>>{
        Espressoldling.increment()
        val result = MutableLiveData<ApiResponse<List<TvResponse>>>()
        handler.postDelayed({
            result.value = ApiResponse.success(jsonHelper.loadTv())
            Espressoldling.decrement()
        }, SERVICE_LATENCY)
        return result
    }

}