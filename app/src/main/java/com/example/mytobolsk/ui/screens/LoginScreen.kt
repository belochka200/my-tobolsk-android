package com.example.mytobolsk.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mytobolsk.R
import com.example.mytobolsk.databinding.FragmentLoginScreenBinding

class LoginScreen : Fragment(R.layout.fragment__login_screen) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding: FragmentLoginScreenBinding = FragmentLoginScreenBinding.bind(view)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}