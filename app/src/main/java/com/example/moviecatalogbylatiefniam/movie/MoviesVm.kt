package com.example.moviecatalogbylatiefniam.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviecatalogbylatiefniam.data.source.local.entity.MovieEntity
import com.example.moviecatalogbylatiefniam.data.source.Repository
import com.example.moviecatalogbylatiefniam.vo.Resource

class MoviesVm(private val repository: Repository):ViewModel() {
    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> = repository.getMovies()
}