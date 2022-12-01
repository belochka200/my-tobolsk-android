package com.example.mytobolsk.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mytobolsk.databinding.ItemStoryItemBinding
import com.example.mytobolsk.ui.models.Story

class StoriesAdapter(
    private val storyDataSet: List<Story>, private val clickListener: (Story) -> Unit
) : RecyclerView.Adapter<StoriesAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemStoryItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemStoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            itemView.setOnClickListener { clickListener(storyDataSet[position]) }
            with(binding) {
                with(storyDataSet[position]) {
                    itemStoryTitle.text = title
                }
            }
        }
    }

    override fun getItemCount(): Int = storyDataSet.size
}