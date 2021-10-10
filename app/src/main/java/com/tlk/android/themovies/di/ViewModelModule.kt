package com.tlk.android.themovies.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tlk.android.themovies.ui.moviedetails.MovieDetailsViewModel
import com.tlk.android.themovies.ui.upcomingandpopularmovies.UpcomingAndPopularMoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(UpcomingAndPopularMoviesViewModel::class)
    internal abstract fun bindUpcomingAndPopularMoviesViewModel(viewModel: UpcomingAndPopularMoviesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailsViewModel::class)
    internal abstract fun bindMovieDetailsViewModel(viewModel: MovieDetailsViewModel): ViewModel
}