package com.example.mytobolsk.ui.screens

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.mytobolsk.R
import com.example.mytobolsk.databinding.FragmentStoryScreenBinding
import com.example.mytobolsk.ui.states.StoryScreenUiState
import com.example.mytobolsk.ui.viewmodels.StoryScreenViewModel

class StoryScreen : Fragment(R.layout.fragment__story_screen) {
    private val storyScreenViewModel: StoryScreenViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding: FragmentStoryScreenBinding = FragmentStoryScreenBinding.bind(view)
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
        storyScreenViewModel.fetchStory(requireArguments().getInt("storyId"))
        storyScreenViewModel.uiState.observe(viewLifecycleOwner) { newState ->
            when (newState) {
                StoryScreenUiState.Error -> {
                    Toast.makeText(
                        requireContext(), "Ошибка загрузки", Toast.LENGTH_LONG
                    ).show()
                }
                StoryScreenUiState.Loading -> {}
                is StoryScreenUiState.Content -> {
                    with(binding) {
                        toolBar.title = newState.title
                        storyDescribe.text = newState.describe
                        storyImage.load(newState.image) {
                            crossfade(500)
                        }
                        toolbar.setOnMenuItemClickListener {
                            when (it.itemId) {
                                R.id.item__share_story -> {
                                    val intent = Intent()
                                    intent.action = Intent.ACTION_SEND
                                    intent.putExtra(
                                        Intent.EXTRA_TEXT,
                                        "История из жизни Твоего Тобольска." + "\n\n${newState.title}" + "\n\n${newState.describe}" + "\n\nПрисоединйся к своему городу!"
                                    )
                                    intent.type = "text/plain"
                                    startActivity(Intent.createChooser(intent, "Кому отправить:"))
                                    true
                                }
                                else -> {
                                    false
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}