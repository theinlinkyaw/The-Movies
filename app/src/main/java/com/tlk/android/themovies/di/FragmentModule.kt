package com.tlk.android.themovies.di

import com.tlk.android.themovies.ui.moviedetails.MovieDetailsFragment
import com.tlk.android.themovies.ui.upcomingandpopularmovies.UpcomingAndPopularMoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun bindUpcomingAndPopularMoviesFragment() : UpcomingAndPopularMoviesFragment

    @ContributesAndroidInjector
    abstract fun bindMovieDetailsFragment() : MovieDetailsFragment
}