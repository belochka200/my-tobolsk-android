package com.example.mytobolsk.ui.screens

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import com.example.mytobolsk.R

class LoginScreen : Fragment(R.layout.fragment__login_screen) {
    // TODO депрекейтед методы
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.forEach {
            it.isVisible = false
        }
        menu.findItem(R.id.menu_item__profile).isVisible = false
    }
}