package com.example.mytobolsk.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mytobolsk.R
import com.example.mytobolsk.databinding.FragmentMainScreenBinding
import com.example.mytobolsk.ui.states.MainScreenUiState
import com.example.mytobolsk.ui.viewmodels.MainScreenViewModel

class MainScreen : Fragment(R.layout.fragment__main_screen) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: MainScreenViewModel by viewModels()
        val binding: FragmentMainScreenBinding = FragmentMainScreenBinding.bind(view)

        viewModel.uiState.observe(viewLifecycleOwner) { newState ->
            when (newState) {
                MainScreenUiState.Error -> {}
                MainScreenUiState.Loading -> {}
                is MainScreenUiState.Content -> {}
            }
        }
    }
}