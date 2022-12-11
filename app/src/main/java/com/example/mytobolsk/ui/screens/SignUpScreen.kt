package com.example.mytobolsk.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mytobolsk.R
import com.example.mytobolsk.databinding.FragmentSignUpScreenBinding

class SignUpScreen : Fragment(R.layout.fragment__sign_up_screen) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding: FragmentSignUpScreenBinding = FragmentSignUpScreenBinding.bind(view)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.toSignInButton.setOnClickListener {
            findNavController().navigate(R.id.action_signUpScreen_to_signInScreen)
        }
    }
}