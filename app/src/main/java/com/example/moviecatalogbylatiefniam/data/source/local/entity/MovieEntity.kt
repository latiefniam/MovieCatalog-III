package com.example.moviecatalogbylatiefniam.data.source.local.entity


import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MovieEntities")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movieId")
    var movieId: String,
    @ColumnInfo(name = "movieTitle")
    var movieTitle: String?,
    @ColumnInfo(name = "movieDuration")
    var movieDuration: String?,
    @ColumnInfo(name = "movieGenre")
    var movieGenre: String?,
    @ColumnInfo(name = "movieDescription")
    var movieDescription: String,
    @ColumnInfo(name = "moviePicture")
    var moviePicture: String,

    @ColumnInfo(name = "movieFav")
    var movieFav: Boolean = false

    )

