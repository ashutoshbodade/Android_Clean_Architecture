package com.ashutosh.androidcleanarchitecture

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CleanApp:Application(){
    override fun onCreate() {
        super.onCreate()
    }
}