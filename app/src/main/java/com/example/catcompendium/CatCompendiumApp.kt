package com.example.catcompendium

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CatCompendiumApp: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}