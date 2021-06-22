package com.example.moviecatalogbylatiefniam.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "TvEntities")
data class TvEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tvId")
    var tvId: String,
    @ColumnInfo(name = "tvTitle")
    var tvTitle: String?,
    @ColumnInfo(name = "tvDuration")
    var tvDuration: String?,
    @ColumnInfo(name = "tvGenre")
    var tvGenre: String?,
    @ColumnInfo(name = "tvDescription")
    var tvDescription: String?,
    @ColumnInfo(name = "tvPicture")
    var tvPicture: String,

    @ColumnInfo(name = "tvFav")
    var tvFav: Boolean = false

)