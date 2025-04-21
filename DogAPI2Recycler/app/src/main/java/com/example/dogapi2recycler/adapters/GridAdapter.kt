package com.example.dogapi2recycler.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dogapi2recycler.R
import com.example.dogapi2recycler.databinding.GridItemBinding
import com.example.dogapi2recycler.model.Dog
import com.example.dogapi2recycler.view.GridActivity

class GridAdapter(var context: GridActivity, var list: ArrayList<Dog>): RecyclerView.Adapter<GridAdapter.MyViewHolder>() {

//    lateinit var bind: GridItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MyViewHolder {
       val bind= GridItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(bind)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int,
    ) {
        val element=list[position]
        holder.onBind(element)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyViewHolder(var bind: GridItemBinding): RecyclerView.ViewHolder(bind.root) {
        var image=bind.image
        var name=bind.name
        var breed=bind.breed
        var life=bind.life

        fun onBind(dog:Dog){
            val imgURL=context.getString(R.string.https_cdn2_thedogapi_com_images)
            var rfId=if(!dog.reference_image_id.equals("HkC31gcNm")) dog.reference_image_id else context.getString(R.string.hjq8ge5v7)
            name.text=if(!dog.name.isNullOrEmpty()) dog.name else context.getString(R.string.dog)
            breed.text= if(!dog.breed_group.isNullOrEmpty()) dog.breed_group else context.getString(R.string.mixed)
            life.text=if(!dog.life_span.isNullOrEmpty()) dog.life_span else context.getString(R.string._10_years)
            Glide.with(context).load(imgURL+rfId+".jpg").into(image)
            bind.item.setOnClickListener {
                context.customClickListener(dog)
            }
        }
    }

    fun setFilteredList(filteredList: ArrayList<Dog>){
        this.list=filteredList
        notifyDataSetChanged()
    }

}