package com.harunbekcan.androidassessment.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.harunbekcan.androidassessment.R
import com.harunbekcan.androidassessment.data.model.Satellite
import com.harunbekcan.androidassessment.databinding.ItemSatellitesBinding
import com.harunbekcan.androidassessment.utils.setVisibilityIfLastItem

class SatellitesAdapter : ListAdapter<Satellite, SatellitesAdapter.SatellitesViewHolder>(DiffCallback) {

    var satelliteItemClick: (Satellite) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SatellitesAdapter.SatellitesViewHolder {
        val binding = ItemSatellitesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SatellitesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SatellitesViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class SatellitesViewHolder(private var binding: ItemSatellitesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Satellite) = with(binding) {
            satelliteNameTextView.text = item.name

            if (item.active) {
                satelliteImageView.setBackgroundResource(R.drawable.bg_satellites_active)
                satelliteStatusTextView.text = itemView.context.resources.getString(R.string.active)
            }

            lineView.setVisibilityIfLastItem(adapterPosition == itemCount - 1 )

            satelliteContainer.setOnClickListener {
                satelliteItemClick(item)
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Satellite>() {

        override fun areItemsTheSame(oldItem: Satellite, newItem: Satellite) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Satellite, newItem: Satellite) =
            oldItem == newItem
    }
}