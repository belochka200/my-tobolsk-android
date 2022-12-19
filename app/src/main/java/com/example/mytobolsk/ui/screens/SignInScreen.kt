package com.example.mytobolsk.ui.screens

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.marginBottom
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mytobolsk.R
import com.example.mytobolsk.databinding.FragmentSignInScreenBinding

class SignInScreen : Fragment(R.layout.fragment__sign_in_screen) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding: FragmentSignInScreenBinding = FragmentSignInScreenBinding.bind(view)
        val toolBar = binding.toolbar
        toolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        ViewCompat.setOnApplyWindowInsetsListener(toolBar) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                topMargin = insets.top
            }
            WindowInsetsCompat.CONSUMED
        }
        ViewCompat.setOnApplyWindowInsetsListener(binding.signInButton) { view, windowsInsets ->
            val insets = windowsInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                bottomMargin = insets.bottom + view.marginBottom
            }
            WindowInsetsCompat.CONSUMED
        }
        binding.toSignUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_signInScreen_to_signUpScreen)
        }
    }
}