package com.example.moviecatalogbylatiefniam.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviecatalogbylatiefniam.data.source.local.entity.MovieEntity
import com.example.moviecatalogbylatiefniam.data.source.local.entity.TvEntity

@Database(
    entities = [MovieEntity::class, TvEntity::class],
    version = 1,
    exportSchema = false
)

abstract class ContentDataBase : RoomDatabase(){
    abstract fun dao() : Dao

    companion object{
        @Volatile
        private var INSTANCE: ContentDataBase? = null

        fun getInstance(context: Context): ContentDataBase=
            INSTANCE?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    ContentDataBase::class.java,
                    "content.db").build().apply {
                    INSTANCE = this
                }
            }
    }
}