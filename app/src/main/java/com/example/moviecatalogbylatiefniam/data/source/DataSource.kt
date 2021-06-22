package com.example.moviecatalogbylatiefniam.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.moviecatalogbylatiefniam.vo.Resource
import com.example.moviecatalogbylatiefniam.data.source.local.entity.MovieEntity
import com.example.moviecatalogbylatiefniam.data.source.local.entity.TvEntity


interface DataSource {
    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>>
    fun getMovieId(movieId:String): LiveData<Resource<MovieEntity>>
    fun setMovFav(movie: MovieEntity, state:Boolean)
    fun getMovFav(): LiveData<PagedList<MovieEntity>>

    fun getTvs():LiveData<Resource<PagedList<TvEntity>>>
    fun getTvId(tvId: String):LiveData<Resource<TvEntity>>
    fun setTvFav(tv: TvEntity, state:Boolean)
    fun getTvFav(): LiveData<PagedList<TvEntity>>


}