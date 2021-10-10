package com.tlk.android.themovies.di

import android.app.Application
import com.tlk.android.themovies.TheMoviesApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AndroidInjectionModule::class,
    AppModule::class,
    ActivityModule::class,
    FragmentModule::class,
    NetworkModule::class,
    StorageModule::class,
    ViewModelModule::class])
interface AppComponent : AndroidInjector<TheMoviesApplication> {

    override fun inject(theMoviesApplication: TheMoviesApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}