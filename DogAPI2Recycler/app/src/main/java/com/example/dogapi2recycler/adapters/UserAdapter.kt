package com.example.dogapi2recycler.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dogapi2recycler.databinding.UserItemBinding
import com.example.dogapi2recycler.model.User
import com.example.dogapi2recycler.view.UserListActivity

class UserAdapter(val context: UserListActivity, val list:List<User>): RecyclerView.Adapter<UserAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MyViewHolder {
        var bind= UserItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(bind)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int,
    ) {
        val element=list[position]
        holder.name.text=element.name
        holder.email.text=element.email
        if(element.active)
            holder.bind.activeStatus.visibility= View.VISIBLE
        else
            holder.bind.activeStatus.visibility=View.GONE
        holder.bind.roundInitial.text=element.name[0].toString()
        holder.bind.userCard.setOnClickListener {
            context.customClickListener(element)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyViewHolder(var bind: UserItemBinding): RecyclerView.ViewHolder(bind.root) {
        val name=bind.userName
        val email=bind.userEmail
    }
}