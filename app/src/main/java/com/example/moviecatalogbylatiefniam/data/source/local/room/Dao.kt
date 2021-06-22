package com.example.moviecatalogbylatiefniam.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.room.Dao
import com.example.moviecatalogbylatiefniam.data.source.local.entity.MovieEntity
import com.example.moviecatalogbylatiefniam.data.source.local.entity.TvEntity


@Dao
interface Dao {
    @Query("SELECT * FROM MovieEntities")
    fun getMovie(): DataSource.Factory<Int, MovieEntity>
    @Query("SELECT * FROM TvEntities")
    fun getTv():DataSource.Factory<Int, TvEntity>

    @Transaction
    @Query("SELECT * FROM MovieEntities WHERE movieId= :movieId")
    fun getMovId(movieId: String): LiveData<MovieEntity>
    @Query("SELECT * FROM TvEntities WHERE tvId= :tvId")
    fun getTvId(tvId: String): LiveData<TvEntity>

    @Query("SELECT * FROM MovieEntities where movieFav= 1")
    fun getMovFav(): DataSource.Factory<Int, MovieEntity>
    @Query("SELECT * FROM TvEntities where tvFav= 1")
    fun getTvFav(): DataSource.Factory<Int, TvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMov(movies: List<MovieEntity>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTv(Tvs: List<TvEntity>)

    @Update
    fun  updateMov(movieEntity: MovieEntity)
    @Update
    fun updateTv(tvEntity: TvEntity)
}