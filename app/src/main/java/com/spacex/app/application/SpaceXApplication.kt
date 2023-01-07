package com.spacex.app.application

import android.app.Application
import com.spacex.app.di.applicationModule
import com.spacex.app.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class SpaceXApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@SpaceXApplication)
            modules(listOf(applicationModule, presentationModule))
        }
    }
}