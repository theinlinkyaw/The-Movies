package com.tlk.android.themovies

import android.content.Context
import androidx.multidex.MultiDex
import com.tlk.android.themovies.di.AppComponent
import com.tlk.android.themovies.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class TheMoviesApplication : DaggerApplication() {

    lateinit var appComponent: AppComponent

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun applicationInjector(): AndroidInjector<out TheMoviesApplication> {
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
        return appComponent
    }
}