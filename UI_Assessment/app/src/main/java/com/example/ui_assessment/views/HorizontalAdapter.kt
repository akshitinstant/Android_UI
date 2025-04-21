package com.example.ui_assessment.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ui_assessment.R
import com.example.ui_assessment.models.FoodItem
import com.example.ui_assessment.databinding.HorizontalRecyclerLayoutBinding

class HorizontalAdapter(val itemList: ArrayList<FoodItem>) :
    RecyclerView.Adapter<HorizontalAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val binding: HorizontalRecyclerLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.horizontal_recycler_layout, parent, false)
        val binding = HorizontalRecyclerLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = itemList.get(position)
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ViewHolder(val binding: HorizontalRecyclerLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FoodItem?) {
            binding.frag1HorizontalRecyclerImg.setImageResource(item?.image ?: R.drawable.ic_launcher_background)
            binding.frag1HorizontalRecyclerName.text = item?.name
            binding.frag1HorizontalRecyclerPrice.text = item?.price
        }
    }
}
