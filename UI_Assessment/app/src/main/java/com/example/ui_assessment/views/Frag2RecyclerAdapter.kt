package com.example.ui_assessment.views

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ui_assessment.R
import com.example.ui_assessment.databinding.Frag2RecyclerLayoutBinding
import com.example.ui_assessment.models.FoodItem

class Frag2RecyclerAdapter(var list: ArrayList<FoodItem>) : RecyclerView.Adapter<Frag2RecyclerAdapter.MyViewHolder>() {

    lateinit var binding: Frag2RecyclerLayoutBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MyViewHolder {
//        binding = DataBindingUtil.inflate( LayoutInflater.from(parent.context), R.layout.frag2_recycler_layout, parent, false)
        binding = Frag2RecyclerLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list[position]
        holder.image.setImageResource(item.image)
        holder.name.text = item.name
        holder.description.text = item.description
        holder.price.text = item.price

    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyViewHolder(view: Frag2RecyclerLayoutBinding) : RecyclerView.ViewHolder(view.root) {
        var image = binding.frag2RecyclerImg
        var name = binding.frag2RecyclerName
        var description= binding.frag2RecyclerDescription
        var price = binding.frag2RecyclerPrice
    }


    fun setFilteredList(filteredList: ArrayList<FoodItem>) {
        this.list = filteredList
        notifyDataSetChanged()
    }
}