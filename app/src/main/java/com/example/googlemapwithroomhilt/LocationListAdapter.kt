package com.example.googlemapwithroomhilt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.googlemapwithroomhilt.databinding.LocationListBinding

class LocationListAdapter(private val listeners: OnClickListeners) :
    RecyclerView.Adapter<LocationListAdapter.ViewHolder>() {


    private lateinit var binding: LocationListBinding
    private var mList = ArrayList<LocationEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        binding = LocationListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = mList[position]
        holder.bind(mList[position])
        binding.imgDelete.setOnClickListener {
            listeners.onDeleteClick(position, item)

        }
        binding.imgEdit.setOnClickListener {
            listeners.onEditClick(position, item)
        }


    }

    override fun getItemCount(): Int {

        return mList.size
    }

    fun setList(listItems: ArrayList<LocationEntity>) {
        mList = listItems
        notifyItemRangeInserted(0, mList.size)
    }

    class ViewHolder(private val binding: LocationListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: LocationEntity) {

            binding.item = item

        }

    }
}