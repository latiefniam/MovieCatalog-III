@file:Suppress("CAST_NEVER_SUCCEEDS")

package com.example.moviecatalogbylatiefniam.detail

import com.example.moviecatalogbylatiefniam.data.source.Repository
import com.example.moviecatalogbylatiefniam.utils.Data
import junit.framework.TestCase.assertNotNull
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogbylatiefniam.data.source.local.entity.MovieEntity
import com.example.moviecatalogbylatiefniam.data.source.local.entity.TvEntity
import com.example.moviecatalogbylatiefniam.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.mockito.Mock
import org.mockito.Mockito.`when`


@RunWith(MockitoJUnitRunner::class)
class DetailVMTest {
    private lateinit var model: DetailVM
    private val dataMovie = Data.generateMovie()[0]
    private val dataTvShow = Data.generateTv()[0]
    private val movieId = dataMovie.movieId
    private val tvId = dataTvShow.tvId

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository
    @Mock
    private lateinit var  movObserver: androidx.lifecycle.Observer<Resource<MovieEntity>>
    @Mock
    private lateinit var tvObserver: androidx.lifecycle.Observer<Resource<TvEntity>>


    @Before
    fun setUp(){
    model = DetailVM(repository)
        model.movSelected(movieId)
        model.tvSelected(tvId)

        }

    @Test
    fun getMovies() {
        val dataMovie = Resource.succes(Data.generateMovie()[0])
        val mov = MutableLiveData<Resource<MovieEntity>>()
        mov.value = dataMovie

        `when`(repository.getMovieId(movieId)).thenReturn(mov)
        model.movie.observeForever(movObserver)
        verify(movObserver).onChanged(dataMovie)
    }

    @Test
    fun getTvShow() {
        val dataTv = Resource.succes(Data.generateTv()[0])
        val tvShow = MutableLiveData<Resource<TvEntity>>()
        tvShow.value = dataTv

        `when`(repository.getTvId(tvId)).thenReturn(tvShow)
        model.tvShow.observeForever(tvObserver)
        verify(tvObserver).onChanged(dataTv)
    }
}