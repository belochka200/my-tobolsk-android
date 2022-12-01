package com.example.mytobolsk.ui.screens

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mytobolsk.R
import com.example.mytobolsk.databinding.FragmentMainScreenBinding
import com.example.mytobolsk.ui.adapters.EventsAdapter
import com.example.mytobolsk.ui.adapters.RoutesAdapter
import com.example.mytobolsk.ui.adapters.StoriesAdapter
import com.example.mytobolsk.ui.models.Event
import com.example.mytobolsk.ui.models.Route
import com.example.mytobolsk.ui.models.Story
import com.example.mytobolsk.ui.states.MainScreenUiState
import com.example.mytobolsk.ui.viewmodels.MainScreenViewModel
import com.google.android.material.appbar.MaterialToolbar

class MainScreen : Fragment(R.layout.fragment__main_screen) {

    private lateinit var binding: FragmentMainScreenBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: MainScreenViewModel by viewModels()
        binding = FragmentMainScreenBinding.bind(view)
        val toolBar: MaterialToolbar = binding.toolbar
        toolBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_item__profile -> {
                    findNavController().navigate(R.id.action_mainScreen_to_loginScreen)
                    true
                }
                R.id.menu_item__notification -> {
                    Toast.makeText(
                        requireContext(),
                        "Уведомлений нет",
                        Toast.LENGTH_SHORT
                    ).show()
                    true
                }
                else -> false
            }
        }

        viewModel.uiState.observe(viewLifecycleOwner) { newState ->
            when (newState) {
                MainScreenUiState.Error -> {
                    Toast.makeText(
                        requireContext(), "Ошибка загрузки", Toast.LENGTH_LONG
                    ).show()
                    showContent(
                        show = false,
                        stories = null,
                        routes = null,
                        events = null
                    )
                }
                MainScreenUiState.Loading -> showContent(
                    show = false,
                    stories = null,
                    routes = null,
                    events = null
                )
                is MainScreenUiState.Content -> showContent(
                    show = true,
                    stories = newState.stories,
                    routes = newState.routes,
                    events = newState.events
                )
            }
        }
    }

    private fun showContent(
        show: Boolean,
        stories: List<Story>?,
        routes: List<Route>?,
        events: List<Event>?
    ) {
        binding.apply {
            headingEvents.isVisible = show
            headingInterestingPlaces.isVisible = show
            headingRoutes.isVisible = show
            headingUseful.isVisible = show
            recyclerViewStories.isVisible = show
            recyclerViewEvents.isVisible = show
            showAllEventsButton.isVisible = show
            showAllRoutesButton.isVisible = show
            showAllInterestingPlacesButton.isVisible = show

            progressCircularBar.isVisible = !show
        }
        if (show && stories != null) {
            binding.apply {
                recyclerViewStories.adapter = StoriesAdapter(stories) { showStoryTitle(it) }
                recyclerViewStories.adapter!!.stateRestorationPolicy =
                    RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            }
        }
        if (show && events != null) {
            binding.recyclerViewEvents.adapter = EventsAdapter(events) { showEvent(it) }
            binding.recyclerViewEvents.adapter!!.stateRestorationPolicy =
                RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
        if (show && routes != null) {
            binding.recyclerViewRoutes.adapter = RoutesAdapter(routes)
            binding.recyclerViewRoutes.adapter!!.stateRestorationPolicy =
                RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
    }

    private fun showStoryTitle(story: Story) {
        Toast.makeText(
            requireContext(),
            story.title,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun showEvent(event: Event) {
        Toast.makeText(
            requireContext(),
            "Мероприятие: ${event.title}",
            Toast.LENGTH_SHORT
        ).show()
    }
}