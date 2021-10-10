package com.tlk.android.themovies.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.tlk.android.themovies.data.db.entities.FavoriteEntity

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favoriteEntity: FavoriteEntity) : Long

    @Delete
    suspend fun removeFavorite(favoriteEntity: FavoriteEntity)
}