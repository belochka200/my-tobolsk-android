package com.example.mytobolsk.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mytobolsk.R
import com.example.mytobolsk.databinding.FragmentSignInScreenBinding

class SignInScreen : Fragment(R.layout.fragment__sign_in_screen) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding: FragmentSignInScreenBinding = FragmentSignInScreenBinding.bind(view)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.toSignUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_signInScreen_to_signUpScreen)
        }
    }
}