package com.example.mytobolsk.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mytobolsk.R
import com.example.mytobolsk.databinding.FragmentDetailEventScreenBinding

class EventDetailScreen : Fragment(R.layout.fragment__detail_event_screen) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding: FragmentDetailEventScreenBinding = FragmentDetailEventScreenBinding.bind(view)
        binding.apply {
            toolbar.title = "Title 2"
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}