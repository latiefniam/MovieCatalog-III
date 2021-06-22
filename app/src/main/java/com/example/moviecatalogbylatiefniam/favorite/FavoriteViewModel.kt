package com.example.moviecatalogbylatiefniam.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviecatalogbylatiefniam.data.source.Repository
import com.example.moviecatalogbylatiefniam.data.source.local.entity.MovieEntity
import com.example.moviecatalogbylatiefniam.data.source.local.entity.TvEntity

class FavoriteViewModel(private val repository: Repository): ViewModel() {

    fun getMovFav(): LiveData<PagedList<MovieEntity>> = repository.getMovFav()
    fun setMovfav(movieEntity: MovieEntity){
        val state = !movieEntity.movieFav
        repository.setMovFav(movieEntity,state)
    }

    fun getTvFav() : LiveData<PagedList<TvEntity>> = repository.getTvFav()
    fun setTvFav(tvEntity: TvEntity){
        val state = !tvEntity.tvFav
        repository.setTvFav(tvEntity, state)
    }

}