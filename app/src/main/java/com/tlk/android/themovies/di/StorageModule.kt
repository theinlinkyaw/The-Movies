package com.tlk.android.themovies.di

import android.content.Context
import com.tlk.android.themovies.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context) = AppDatabase.getInstance(context)

    @Singleton
    @Provides
    fun provideFavoriteDao(db: AppDatabase) = db.favoriteDao()

    @Singleton
    @Provides
    fun provideMovieDao(db: AppDatabase) = db.movieDao()

    @Singleton
    @Provides
    fun providePopularDao(db: AppDatabase) = db.popularDao()

    @Singleton
    @Provides
    fun provideUpcomingDao(db: AppDatabase) = db.upcomingDao()
}