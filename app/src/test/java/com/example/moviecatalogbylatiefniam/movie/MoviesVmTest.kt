package com.example.moviecatalogbylatiefniam.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.moviecatalogbylatiefniam.data.source.local.entity.MovieEntity
import com.example.moviecatalogbylatiefniam.data.source.Repository
import com.example.moviecatalogbylatiefniam.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesVmTest {
    private lateinit var model: MoviesVm

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    @Mock
    private lateinit var pageList: PagedList<MovieEntity>

    @Mock
    private lateinit var repository: Repository
    @Mock
    private lateinit var  movObserver: androidx.lifecycle.Observer<Resource<PagedList<MovieEntity>>>



    @Before
    fun setUp(){
        model = MoviesVm(repository)

    }

    @Test
    fun getMovies() {
       val dataMov = Resource.succes(pageList)
        `when`(dataMov.data?.size).thenReturn(11)
        val movie = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movie.value=dataMov
        `when`(repository.getMovies()).thenReturn(movie)
        val movieEntity = model.getMovies().value?.data
        assertNotNull(movieEntity)
        assertEquals(11, movieEntity?.size)

        model.getMovies().observeForever(movObserver)
        verify(movObserver).onChanged(dataMov)

    }
}