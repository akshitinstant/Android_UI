package com.example.dogapi2recycler.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dogapi2recycler.R
import com.example.dogapi2recycler.databinding.ListItemBinding
import com.example.dogapi2recycler.model.Dog
import com.example.dogapi2recycler.view.ListActivity
import com.example.dogapi2recycler.viewmodel.MyViewModel

class ListAdapter(var context: ListActivity,var list: ArrayList<Dog>) : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

//    lateinit var bind: ListItemBinding    //causes all ViewHolders to reference the same layout binding, leading to click misbehavior and visual glitches.
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MyViewHolder {
        val bind = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(bind)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int,
    ) {
        var element = list[position]
        holder.onBind(element)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyViewHolder(var bind: ListItemBinding) : RecyclerView.ViewHolder(bind.root) {
        var image = bind.img
        var name = bind.name
        var breed = bind.breed
        var life = bind.life

        fun onBind(dog: Dog){
            var rfId= if(!dog.reference_image_id.equals("HkC31gcNm")) dog.reference_image_id else context.getString(R.string.hjq8ge5v7)
            val imgURL= context.getString(R.string.https_cdn2_thedogapi_com_images)
            name.text=if(!dog.name.isNullOrEmpty()) dog.name else context.getString(R.string.dog)
            breed.text= if(!dog.breed_group.isNullOrEmpty()) dog.breed_group else context.getString(R.string.breed_group)
            life.text=if(!dog.life_span.isNullOrEmpty()) dog.life_span else context.getString(R.string._10_years)
            var imgLink=imgURL+rfId+".jpg"
            Glide.with(context).load(imgLink).into(image)
            bind.itemCard.setOnClickListener {
                context.customClickListener(dog)
            }
        }
    }

    fun setFilteredList(filteredList: ArrayList<Dog>) {
        this.list = filteredList
        notifyDataSetChanged()
    }

}