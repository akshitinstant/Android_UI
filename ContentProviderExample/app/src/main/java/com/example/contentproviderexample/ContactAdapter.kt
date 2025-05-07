package com.example.contentproviderexample


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contentproviderexample.databinding.ContactItemBinding

class ContactAdapter(private var list:List<String>): RecyclerView.Adapter<ContactAdapter.MyViewHolder>() {


    inner class MyViewHolder(val bind: ContactItemBinding): RecyclerView.ViewHolder(bind.root) {
        val name=bind.name
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ContactAdapter.MyViewHolder {
        val bind= ContactItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(bind)
    }

    override fun onBindViewHolder(holder: ContactAdapter.MyViewHolder, position: Int) {
        holder.bind.name.text=list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(newList: List<String>) {
        list = newList
        notifyDataSetChanged()
    }
}