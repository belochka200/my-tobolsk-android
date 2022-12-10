package com.example.mytobolsk.ui.screens

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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
        val mainScreenViewModel: MainScreenViewModel by viewModels()
        binding = FragmentMainScreenBinding.bind(view)
        val swipeRefreshLayout: SwipeRefreshLayout = binding.swipeToRefresh
        swipeRefreshLayout.setOnRefreshListener {
            mainScreenViewModel.fetchData()
            swipeRefreshLayout.isRefreshing = false
        }
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

        mainScreenViewModel.uiState.observe(viewLifecycleOwner) { newState ->
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
            recyclerViewRoutes.isVisible = show
            showAllEventsButton.isVisible = show
            showAllRoutesButton.isVisible = show
            showAllInterestingPlacesButton.isVisible = show

            progressHorizontalBar.isVisible = !show
//            swipeToRefresh.isRefreshing = !show
        }
        if (show && stories != null) {
            binding.apply {
                recyclerViewStories.adapter = StoriesAdapter(stories) { showStory(it.id) }
                recyclerViewStories.adapter!!.stateRestorationPolicy =
                    RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            }
        }
        if (show && events != null) {
            binding.recyclerViewEvents.adapter = EventsAdapter(
                events,
                clickListener = { showEvent(it) },
//                bookmarked = { bookmarkedEvent(it) }
            )
            binding.recyclerViewEvents.adapter!!.stateRestorationPolicy =
                RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
        if (show && routes != null) {
            binding.recyclerViewRoutes.adapter = RoutesAdapter(routes)
            binding.recyclerViewRoutes.adapter!!.stateRestorationPolicy =
                RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
    }

    private fun showStory(storyId: Int) {
        val bundle = bundleOf("storyId" to storyId)
        findNavController().navigate(R.id.action_mainScreen_to_storyScreen, bundle)
    }

    private fun showEvent(event: Event) {
        findNavController().navigate(R.id.action_mainScreen_to_eventDetailScreen)
    }

//    private fun bookmarkedEvent(event: Event) {
//
//    }
}