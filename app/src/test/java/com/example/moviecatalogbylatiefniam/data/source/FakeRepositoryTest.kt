package com.example.moviecatalogbylatiefniam.data.source


import androidx.lifecycle.LiveData

import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviecatalogbylatiefniam.data.source.local.LocalDataSource
import com.example.moviecatalogbylatiefniam.data.source.local.entity.MovieEntity
import com.example.moviecatalogbylatiefniam.data.source.local.entity.TvEntity
import com.example.moviecatalogbylatiefniam.data.source.remote.RemoteDataSource
import com.example.moviecatalogbylatiefniam.data.source.remote.response.ApiResponse
import com.example.moviecatalogbylatiefniam.data.source.remote.response.MovResponse
import com.example.moviecatalogbylatiefniam.data.source.remote.response.TvResponse
import com.example.moviecatalogbylatiefniam.utils.AppExecutor
import com.example.moviecatalogbylatiefniam.vo.Resource

class FakeRepositoryTest (
    private val remoteDS: RemoteDataSource,
    private val localDS: LocalDataSource,
    private val executor: AppExecutor): DataSource {


    override fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, List<MovResponse>>(executor) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder().setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDS.getMovie(), config).build()
            }

            override fun createCall(): LiveData<ApiResponse<List<MovResponse>>> = remoteDS.getMovies()


            override fun saveCallResult(data: List<MovResponse>) {
                val list = ArrayList<MovieEntity>()

                for (response in data){
                    val movie = MovieEntity(
                        response.movieId.toString(), response.movieTitle, response.movieDuration,
                        response.movieGenre, response.movieDescription, response.moviePicture, false
                    )
                    list.add(movie)
                }
                localDS.insertMov(list)
            }

            override fun shouldFecth(data: PagedList<MovieEntity>): Boolean =
                data.isEmpty()

        }.asLiveData()

    }
    override fun getMovieId(movieId: String): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, List<MovResponse>>(executor) {
            override fun loadFromDB(): LiveData<MovieEntity> =
                localDS.getMovId(movieId)

            override fun createCall(): LiveData<ApiResponse<List<MovResponse>>> =
                remoteDS.getMovies()

            override fun saveCallResult(data: List<MovResponse>) {
                val list = ArrayList<MovieEntity>()
                for (response in data){
                    val movie = MovieEntity(
                        response.movieId.toString(), response.movieTitle, response.movieDuration,
                        response.movieGenre, response.movieDescription, response.moviePicture,
                        false
                    )
                    list.add(movie)
                }
                localDS.insertMov(list)
            }

            override fun shouldFecth(data: MovieEntity): Boolean =
                false

        }.asLiveData()
    }

    override fun setMovFav(movie: MovieEntity, state: Boolean) {
        executor.diskIO().execute { localDS.setMovFav(movie, state) }
    }

    override fun getMovFav(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder().setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4).setPageSize(4).build()
        return LivePagedListBuilder(localDS.getMovFav(), config).build()
    }


    override fun getTvs(): LiveData<Resource<PagedList<TvEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvEntity>, List<TvResponse>>(executor) {
            override fun loadFromDB(): LiveData<PagedList<TvEntity>> {
                val config = PagedList.Config.Builder().setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDS.getTv(), config).build()
            }

            override fun createCall(): LiveData<ApiResponse<List<TvResponse>>> = remoteDS.getTvShows()


            override fun saveCallResult(data: List<TvResponse>) {
                val list = ArrayList<TvEntity>()

                for (response in data){
                    val tv = TvEntity(
                        response.tvId.toString(), response.tvTitle, response.tvDuration,
                        response.tvGenre, response.tvDescription, response.tvPicture, false
                    )
                    list.add(tv)
                }
                localDS.insertTv(list)
            }

            override fun shouldFecth(data: PagedList<TvEntity>): Boolean =
                data.isEmpty()



        }.asLiveData()

    }
    override fun getTvId(tvId: String): LiveData<Resource<TvEntity>> {
        return object : NetworkBoundResource<TvEntity, List<TvResponse>>(executor) {
            override fun loadFromDB(): LiveData<TvEntity> =
                localDS.getTvId(tvId)

            override fun createCall(): LiveData<ApiResponse<List<TvResponse>>> =
                remoteDS.getTvShows()

            override fun saveCallResult(data: List<TvResponse>) {
                val list = ArrayList<TvEntity>()
                for (response in data){
                    val tv = TvEntity(
                        response.tvId.toString(), response.tvTitle, response.tvDuration,
                        response.tvGenre, response.tvDescription, response.tvPicture,
                        false
                    )
                    list.add(tv)
                }
                localDS.insertTv(list)
            }

            override fun shouldFecth(data: TvEntity): Boolean =
                false

        }.asLiveData()
    }

    override fun setTvFav(tv: TvEntity, state: Boolean) {
        executor.diskIO().execute { localDS.setTvFav(tv, state) }
    }

    override fun getTvFav(): LiveData<PagedList<TvEntity>> {
        val config = PagedList.Config.Builder().setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4).setPageSize(4).build()
        return LivePagedListBuilder(localDS.getTvFav(), config).build()
    }


}