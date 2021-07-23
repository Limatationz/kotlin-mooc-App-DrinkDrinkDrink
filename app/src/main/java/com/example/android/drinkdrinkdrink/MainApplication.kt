package com.example.android.drinkdrinkdrink

import android.app.Application
import timber.log.Timber

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        //Initialisierung von Timber
        Timber.plant(Timber.DebugTree())
    }
}