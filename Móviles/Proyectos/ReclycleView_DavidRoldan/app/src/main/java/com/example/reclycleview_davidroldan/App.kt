package com.example.reclycleview_davidroldan

import android.app.Application
import timber.log.Timber
import timber.log.Timber.*

class App : Application(){

        override fun onCreate() {
            super.onCreate()
            Timber.plant(DebugTree())
        }
}