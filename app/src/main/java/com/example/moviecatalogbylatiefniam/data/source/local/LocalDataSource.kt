package com.example.moviecatalogbylatiefniam.data.source.local

import androidx.lifecycle.LiveData
import com.example.moviecatalogbylatiefniam.data.source.local.entity.MovieEntity
import androidx.paging.DataSource
import com.example.moviecatalogbylatiefniam.data.source.local.entity.TvEntity
import com.example.moviecatalogbylatiefniam.data.source.local.room.Dao

class LocalDataSource private constructor(
    private val mContentDao: Dao){

    companion object{
        private var instance : LocalDataSource? = null

        fun getInstance(mDao: Dao): LocalDataSource=
            instance?: LocalDataSource(mDao).apply {
                instance = this
            }
    }

    fun getMovie():DataSource.Factory<Int, MovieEntity> = mContentDao.getMovie()
    fun getMovId(movieId: String): LiveData<MovieEntity> =
    mContentDao.getMovId(movieId)
    fun insertMov(movies: List<MovieEntity>){
        mContentDao.insertMov(movies) }
    fun getMovFav(): DataSource.Factory<Int, MovieEntity> =
        mContentDao.getMovFav()
    fun setMovFav(movie: MovieEntity, newState: Boolean){
        movie.movieFav = newState
        mContentDao.updateMov(movie)
    }


    fun getTv(): DataSource.Factory<Int, TvEntity> = mContentDao.getTv()
    fun getTvId(tvId: String): LiveData<TvEntity> =
        mContentDao.getTvId(tvId)
    fun insertTv(tvs: List<TvEntity>){
        mContentDao.insertTv(tvs) }
    fun getTvFav(): DataSource.Factory<Int, TvEntity> =
        mContentDao.getTvFav()
    fun setTvFav(tv: TvEntity, newState: Boolean){
        tv.tvFav = newState
        mContentDao.updateTv(tv)
    }
}