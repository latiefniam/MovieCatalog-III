package com.example.moviecatalogbylatiefniam.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.moviecatalogbylatiefniam.data.source.local.LocalDataSource
import com.example.moviecatalogbylatiefniam.data.source.local.entity.MovieEntity
import com.example.moviecatalogbylatiefniam.data.source.local.entity.TvEntity
import com.example.moviecatalogbylatiefniam.data.source.remote.RemoteDataSource
import com.example.moviecatalogbylatiefniam.utils.AppExecutor
import com.example.moviecatalogbylatiefniam.utils.Data
import com.example.moviecatalogbylatiefniam.vo.Resource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull

import org.junit.Test
import org.junit.Rule
import org.mockito.Mockito.`when`

import org.mockito.Mockito.mock



class RepositoryTest {

    @get:Rule
    var instantTaskExecutorRule= InstantTaskExecutorRule()

    private val executor = mock(AppExecutor::class.java)
    private val localDS = mock(LocalDataSource::class.java)
    private val remote = mock(RemoteDataSource::class.java)
    private val repository = FakeRepositoryTest(remote,localDS,executor)
    private val movResponse = Data.generateRemoteMovie()
    private val tvResponse = Data.generateRemoteTv()




    @Test
    fun getMovies() {
        val dataFactory = mock(androidx.paging.DataSource.Factory::class.java) as
                androidx.paging.DataSource.Factory<Int, MovieEntity>
        `when`(localDS.getMovie()).thenReturn(dataFactory)
        repository.getMovies()

        val movieEntity = Resource.succes(PageListTestUtil.mockPagedList(Data
            .generateMovie()))
        verify(localDS).getMovie()
        assertNotNull(movieEntity.data)
        assertEquals(movResponse.size.toLong(), movieEntity.data?.size?.toLong())
    }
    @Test
    fun getMovFav(){
        val dataFactory = mock(androidx.paging.DataSource.Factory::class.java) as
                androidx.paging.DataSource.Factory<Int, MovieEntity>
        `when`(localDS.getMovFav()).thenReturn(dataFactory)
        repository.getMovFav()

        val movieEntity = Resource.succes(PageListTestUtil.mockPagedList(Data
            .generateMovie()))
        verify(localDS).getMovFav()
        assertNotNull(movieEntity.data)
        assertEquals(movResponse.size.toLong(), movieEntity.data?.size?.toLong())
    }


    @Test
    fun getTvs() {
        val dataFactory = mock(androidx.paging.DataSource.Factory::class.java) as
                androidx.paging.DataSource.Factory<Int, TvEntity>
        `when`(localDS.getTv()).thenReturn(dataFactory)
        repository.getTvs()

        val tvEntity = Resource.succes(PageListTestUtil.mockPagedList(Data
            .generateTv()))
        verify(localDS).getTv()
        assertNotNull(tvEntity.data)
        assertEquals(tvResponse.size.toLong(), tvEntity.data?.size?.toLong())

    }
    @Test
    fun getTvFav(){
        val dataFactory = mock(androidx.paging.DataSource.Factory::class.java) as
                androidx.paging.DataSource.Factory<Int, TvEntity>
        `when`(localDS.getTvFav()).thenReturn(dataFactory)
        repository.getTvFav()

        val tvEntity = Resource.succes(PageListTestUtil.mockPagedList(Data
            .generateTv()))
        verify(localDS).getTvFav()
        assertNotNull(tvEntity.data)
        assertEquals(tvResponse.size.toLong(), tvEntity.data?.size?.toLong())
    }

}