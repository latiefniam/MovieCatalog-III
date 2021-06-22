package com.example.moviecatalogbylatiefniam.di

import android.content.Context
import com.example.moviecatalogbylatiefniam.data.source.Repository
import com.example.moviecatalogbylatiefniam.data.source.local.LocalDataSource
import com.example.moviecatalogbylatiefniam.data.source.local.room.ContentDataBase
import com.example.moviecatalogbylatiefniam.data.source.remote.RemoteDataSource
import com.example.moviecatalogbylatiefniam.utils.AppExecutor
import com.example.moviecatalogbylatiefniam.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context):Repository{
        val database = ContentDataBase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDS = LocalDataSource.getInstance(database.dao())
        val executor = AppExecutor()

        return Repository.getInstance(remoteDataSource, localDS, executor)
    }
}