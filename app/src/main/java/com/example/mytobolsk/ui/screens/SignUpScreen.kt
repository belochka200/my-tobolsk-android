package com.example.mytobolsk.ui.screens

import android.os.Bundle
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.marginBottom
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mytobolsk.R
import com.example.mytobolsk.databinding.FragmentSignUpScreenBinding

class SignUpScreen : Fragment(R.layout.fragment__sign_up_screen) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding: FragmentSignUpScreenBinding = FragmentSignUpScreenBinding.bind(view)
        val toolBar = binding.toolbar
        toolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        ViewCompat.setOnApplyWindowInsetsListener(toolBar) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updateLayoutParams<MarginLayoutParams> {
                topMargin = insets.top
            }
            WindowInsetsCompat.CONSUMED
        }
        ViewCompat.setOnApplyWindowInsetsListener(binding.signUpButton) { view, windowsInsets ->
            val insets = windowsInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updateLayoutParams<MarginLayoutParams> {
                bottomMargin = insets.bottom + view.marginBottom
            }
            WindowInsetsCompat.CONSUMED
        }
        binding.toSignInButton.setOnClickListener {
            findNavController().navigate(R.id.action_signUpScreen_to_signInScreen)
        }
    }
}