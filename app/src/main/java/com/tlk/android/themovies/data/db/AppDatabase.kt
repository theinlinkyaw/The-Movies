package com.tlk.android.themovies.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tlk.android.themovies.data.db.entities.FavoriteEntity
import com.tlk.android.themovies.data.db.entities.MovieEntity
import com.tlk.android.themovies.data.db.entities.PopularEntity
import com.tlk.android.themovies.data.db.entities.UpcomingEntity

@Database(
    entities =
    [
        FavoriteEntity::class,
        MovieEntity::class,
        PopularEntity::class,
        UpcomingEntity::class
    ],
    version = 1,
    exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "tm.db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        private fun create(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) { INSTANCE ?: create(context).also { INSTANCE = it } }
    }

    abstract fun favoriteDao(): FavoriteDao

    abstract fun movieDao(): MovieDao

    abstract fun popularDao(): PopularDao

    abstract fun upcomingDao(): UpcomingDao
}