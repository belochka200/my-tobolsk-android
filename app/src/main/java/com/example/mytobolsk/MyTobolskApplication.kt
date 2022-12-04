package com.example.mytobolsk

import android.app.Application
import android.os.Build
import android.util.Log
import com.google.android.material.color.DynamicColors

class MyTobolskApplication : Application() {
    override fun onCreate() {
        super.onCreate()
//      TODO DynamicColors apply to activities if available
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}