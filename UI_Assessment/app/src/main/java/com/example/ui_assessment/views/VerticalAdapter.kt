package com.example.ui_assessment.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ui_assessment.databinding.VerticalRecyclerLayoutBinding
import com.example.ui_assessment.models.FoodList

class VerticalAdapter(private var categoryList: ArrayList<FoodList>?) :
    RecyclerView.Adapter<VerticalAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val binding: VerticalRecyclerLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.vertical_recycler_layout, parent, false)
        val binding = VerticalRecyclerLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = categoryList?.get(position)
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return categoryList?.size ?: 0
    }

    inner class ViewHolder(private val binding: VerticalRecyclerLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: FoodList?) {
            binding.frag1VerticalRecyclerTxt.text = item?.category
            val horizontalAdapter = HorizontalAdapter(item?.items?: arrayListOf())

            binding.frag1HorizontalRecycler.layoutManager =
                LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)

            binding.frag1HorizontalRecycler.adapter = horizontalAdapter

        }
    }

    fun updateList(newList: ArrayList<FoodList>){
        categoryList=newList
        notifyDataSetChanged()
    }
}
