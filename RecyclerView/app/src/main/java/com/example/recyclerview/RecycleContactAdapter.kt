package com.example.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecycleContactAdapter(
    val arrContacts: ArrayList<ContactModel>,
    val listener: CustomClickListener,
) :
    RecyclerView.Adapter<RecycleContactAdapter.ViewHolder>() {

    public class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val txtName: TextView = view.findViewById(R.id.name)
        val txtNumber: TextView = view.findViewById(R.id.number)
        val image: ImageView = view.findViewById(R.id.imgContact)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_row, parent, false)
        val viewHolder = ViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.image.setImageResource(arrContacts[position].img)
        holder.txtName.text = arrContacts[position].name
        holder.txtNumber.text = arrContacts[position].number

        holder.itemView.setOnClickListener {
            listener.cutomClickListener(holder.itemView, position)
        }

    }

    override fun getItemCount(): Int {
        return arrContacts.size
    }

    fun addItem(contact: ContactModel) {
        this.arrContacts.add(contact)
        notifyItemRangeInserted(this.arrContacts.size - 1, this.arrContacts.size)
    }

    fun addList(contacts: ArrayList<ContactModel>) {
//        arrContacts.clear()
        arrContacts.addAll(contacts)
        notifyItemRangeChanged(arrContacts.size - contacts.size, arrContacts.size)
    }

    fun updateItem(position: Int) {
        arrContacts[position] =
            ContactModel(R.drawable.ic_launcher_foreground, "Item Updated", "1234")
        notifyItemChanged(position)
    }

    fun deleteItem(position: Int) {
        arrContacts.removeAt(position)
        notifyItemRemoved(position)
    }

}