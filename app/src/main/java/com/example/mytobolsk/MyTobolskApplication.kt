package com.example.mytobolsk

import android.app.Application
import android.util.Log
import com.google.android.material.color.DynamicColors

class MyTobolskApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}