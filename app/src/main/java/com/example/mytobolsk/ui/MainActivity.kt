package com.example.mytobolsk.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mytobolsk.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
    }
}