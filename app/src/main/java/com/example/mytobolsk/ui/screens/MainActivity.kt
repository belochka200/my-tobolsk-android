package com.example.mytobolsk.ui.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.mytobolsk.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        when (item.itemId) {
            R.id.menu_item__profile ->
                Toast.makeText(
                    this,
                    "Аккаунт",
                    Toast.LENGTH_LONG
                ).show()
            R.id.menu_item__notification -> {
                Toast.makeText(
                    this,
                    "Уведомлений нет",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        return true
    }
}