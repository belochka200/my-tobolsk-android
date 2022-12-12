package com.example.mytobolsk.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mytobolsk.databinding.ItemStoryCardBinding
import com.example.mytobolsk.ui.models.Story

class StoriesAdapter(
    private val storyDataSet: List<Story>,
    private val clickListener: (Story) -> Unit
) : RecyclerView.Adapter<StoriesAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemStoryCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemStoryCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            itemView.setOnClickListener { clickListener(storyDataSet[position]) }
            with(binding) {
                with(storyDataSet[position]) {
                    itemStoryTitle.text = title
                    itemStoryImage.load(storyDataSet[position].image) {
                        crossfade(500)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = storyDataSet.size
}