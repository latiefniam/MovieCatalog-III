@file:Suppress("DEPRECATION")

package com.example.moviecatalogbylatiefniam.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.moviecatalogbylatiefniam.data.source.Repository
import com.example.moviecatalogbylatiefniam.data.source.local.entity.MovieEntity
import com.example.moviecatalogbylatiefniam.data.source.local.entity.TvEntity
import com.example.moviecatalogbylatiefniam.utils.Data
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {

    private lateinit var model : FavoriteViewModel

    @get:Rule
    var instantExecutoRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var pageListMov: PagedList<MovieEntity>
    @Mock
    private lateinit var pagedListTv: PagedList<TvEntity>
    @Mock
    private lateinit var repository: Repository
    @Mock
    private lateinit var movObserver: androidx.lifecycle.Observer<PagedList<MovieEntity>>
    @Mock
    private lateinit var tvObserver: androidx.lifecycle.Observer<PagedList<TvEntity>>

    private var dataMov = Data.generateMovie()[0]
    private var dataTv= Data.generateTv()[0]


    @Before
    fun setUp(){
        model = FavoriteViewModel(repository)
    }

    @Test
    fun getMovFav(){
        val movData = pageListMov
        `when`(movData.size).thenReturn(11)
        val movie = MutableLiveData<PagedList<MovieEntity>>()
        movie.value = movData

        `when`(repository.getMovFav()).thenReturn(movie)
        val movEntity = model.getMovFav().value
        verify(repository).getMovFav()
        assertNotNull(movEntity)
        assertEquals(11, movEntity?.size)

        model.getMovFav().observeForever(movObserver)
        verify(movObserver).onChanged(movData)

    }
    @Test
    fun setMovFav(){
        model.setMovfav(dataMov)
        verify(repository).setMovFav(dataMov, true)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun getTvFav(){
        val tvData = pagedListTv
        `when`(tvData.size).thenReturn(11)
        val tvShow = MutableLiveData<PagedList<TvEntity>>()
        tvShow.value = tvData

        `when`(repository.getTvFav()).thenReturn(tvShow)
        val tvEntity = model.getTvFav().value
        verify(repository).getTvFav()
        assertNotNull(tvEntity)
        assertEquals(11, tvEntity?.size)

        model.getTvFav().observeForever(tvObserver)
        verify(tvObserver).onChanged(tvData)

    }
    @Test
    fun setTvFav(){
        model.setTvFav(dataTv)
        verify(repository).setTvFav(dataTv, true)
        verifyNoMoreInteractions(repository)
    }
}