package com.tlk.android.themovies.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tlk.android.themovies.data.api.reponses.Upcoming

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val posterPath: String?,
    val backdropPath: String?,
    val overview: String,
    val releaseDate: String,
    val popularity: Float,
    val voteCount: Int,
    val voteAverage: Float
)