package com.tlk.android.themovies.data.db.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "popular",
    foreignKeys = [ForeignKey(
        entity = MovieEntity::class,
        parentColumns = ["id"],
        childColumns = ["movieId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class PopularEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val movieId: Int
)
