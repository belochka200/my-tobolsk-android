package com.example.mytobolsk.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mytobolsk.R
import com.example.mytobolsk.databinding.FragmentStoryScreenBinding
import com.example.mytobolsk.ui.viewmodels.StoryScreenViewModel

class StoryScreen : Fragment(R.layout.fragment__story_screen) {
    private val storyScreenViewModel: StoryScreenViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding: FragmentStoryScreenBinding = FragmentStoryScreenBinding.bind(view)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}