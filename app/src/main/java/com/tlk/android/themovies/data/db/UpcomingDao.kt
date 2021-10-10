package com.tlk.android.themovies.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.tlk.android.themovies.data.db.entities.UpcomingEntity

@Dao
interface UpcomingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUpcoming(upcomingEntities: List<UpcomingEntity>)

}