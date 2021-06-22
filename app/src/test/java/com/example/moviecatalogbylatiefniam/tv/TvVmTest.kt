package com.example.moviecatalogbylatiefniam.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.moviecatalogbylatiefniam.data.source.local.entity.TvEntity
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
class TvVmTest {
    private lateinit var model: TvVm

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    @Mock
    private lateinit var pageList: PagedList<TvEntity>

    @Mock
    private lateinit var repository: Repository
    @Mock
    private lateinit var  tvObserver: androidx.lifecycle.Observer<Resource<PagedList<TvEntity>>>




    @Before
    fun setUp(){
        model = TvVm(repository)
    }
    @Test
    fun getTvShows() {
        val dataTv = Resource.succes(pageList)
        `when`(dataTv.data?.size).thenReturn(11)
        val tvShow = MutableLiveData<Resource<PagedList<TvEntity>>>()
        tvShow.value=dataTv
        `when`(repository.getTvs()).thenReturn(tvShow)
        val tvEntity = model.getTvShows().value?.data
        assertNotNull(tvEntity)
        assertEquals(11, tvEntity?.size)

        model.getTvShows().observeForever(tvObserver)
        verify(tvObserver).onChanged(dataTv)
    }
}