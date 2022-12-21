package com.example.mytobolsk.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mytobolsk.databinding.ItemRouteCardBinding
import com.example.mytobolsk.ui.models.Route

class RoutesAdapter(private val eventsDataset: List<Route>) :
    RecyclerView.Adapter<RoutesAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemRouteCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRouteCardBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(eventsDataset[position]) {
                with(binding) {
                    itemRouteCardTitle.text = title
                    itemRouteImage.load(image) { crossfade(500) }
                }
            }
        }
    }

    override fun getItemCount(): Int = eventsDataset.size
}
