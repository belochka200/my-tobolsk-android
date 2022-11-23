package com.example.mytobolsk.ui.screens

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mytobolsk.R
import com.example.mytobolsk.databinding.FragmentMainScreenBinding
import com.example.mytobolsk.ui.adapters.StoriesAdapter
import com.example.mytobolsk.ui.models.Route
import com.example.mytobolsk.ui.models.Story
import com.example.mytobolsk.ui.states.MainScreenUiState
import com.example.mytobolsk.ui.viewmodels.MainScreenViewModel

class MainScreen : Fragment(R.layout.fragment__main_screen) {

    private lateinit var binding: FragmentMainScreenBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: MainScreenViewModel by viewModels()
        binding = FragmentMainScreenBinding.bind(view)
        viewModel.uiState.observe(viewLifecycleOwner) { newState ->
            when (newState) {
                MainScreenUiState.Error -> Toast.makeText(
                    requireContext(), "Ошибка загрузки", Toast.LENGTH_LONG
                ).show()
                MainScreenUiState.Loading -> showContent(
                    show = false,
                    stories = null,
                    routes = null
                )
                is MainScreenUiState.Content -> showContent(
                    show = true,
                    stories = newState.stories,
                    routes = newState.routes
                )
            }
        }
    }

    private fun showContent(
        show: Boolean,
        stories: List<Story>?,
        routes: List<Route>?
    ) {
        binding.apply {
            headingEvents.isVisible = show
            headingPlaces.isVisible = show
            headingRoutes.isVisible = show
            recyclerViewStories.isVisible = show

            progressCircularBar.isVisible = !show
        }
        if (show && stories != null)
            binding.recyclerViewStories.adapter = StoriesAdapter(stories)
    }
}