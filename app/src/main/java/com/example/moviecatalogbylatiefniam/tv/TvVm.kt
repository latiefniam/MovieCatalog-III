package com.example.moviecatalogbylatiefniam.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviecatalogbylatiefniam.data.source.local.entity.TvEntity
import com.example.moviecatalogbylatiefniam.data.source.Repository
import com.example.moviecatalogbylatiefniam.vo.Resource

class TvVm(private val repository: Repository): ViewModel() {
    fun getTvShows(): LiveData<Resource<PagedList<TvEntity>>> = repository.getTvs()
}