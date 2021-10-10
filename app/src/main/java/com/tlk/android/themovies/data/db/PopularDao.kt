package com.tlk.android.themovies.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.tlk.android.themovies.data.db.entities.PopularEntity

@Dao
interface PopularDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPopular(popularEntities: List<PopularEntity>)

}