package com.example.mytobolsk.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mytobolsk.R
import com.example.mytobolsk.databinding.ItemEventCardBinding
import com.example.mytobolsk.ui.models.Event

class EventsAdapter(
    private val eventsDataset: List<Event>,
    private val clickListener: (Event) -> Unit,
//    private val bookmarked: (Event) -> Unit
) : RecyclerView.Adapter<EventsAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemEventCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemEventCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            itemView.setOnClickListener { clickListener(eventsDataset[position]) }

            with(eventsDataset[position]) {
                binding.itemEventCardTitle.text = title
                binding.itemEventCardPlace.text =
                    holder.itemView.context.getString(R.string.`where`, place)
                binding.itemEventCardTime.text =
                    holder.itemView.context.getString(R.string.`when`, date, time)
//                binding.checkbox.isChecked = bookmarked
//                binding.checkbox.setOnClickListener { bookmarked(this) }
            }
        }
    }

    override fun getItemCount(): Int = eventsDataset.size
}