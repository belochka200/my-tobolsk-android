package com.example.mytobolsk.ui.screens

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.mytobolsk.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class StoryDetailModalBottomSheet(
    private val title: String,
    private val describe: String
) : BottomSheetDialogFragment(R.layout.fragment__detail_story_screen) {

    companion object {
        const val TAG = "StoryDetailBottomSheet"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.story_detail_title).text = title
        view.findViewById<TextView>(R.id.story_detail_describe).text = describe
    }
}