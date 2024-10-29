package com.example.todolist

import android.icu.text.Transliterator.Position
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(list:ArrayList<String>,val remotePosition: (Int) -> Unit ): RecyclerView.Adapter<MyAdapter.ItemViewHolder>(){

    var myList = list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_design, parent, false)
        return ItemViewHolder(view)

    }

    override fun onBindViewHolder(holder: MyAdapter.ItemViewHolder, position: Int) {
        val item = myList[position]
        holder.flagName.text = myList[position]
        holder.itemView.setOnClickListener {
           // remotePosition(position)
        }

        }


    override fun getItemCount(): Int {
        return myList.size
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val flagName: TextView = itemView.findViewById(R.id.editText2)
    }

    fun addItem(item: String) {
        myList.add(0, item) // Add item at the top
        notifyItemInserted(0)
    }
    fun removeItem(position: Int) {
        myList.removeAt(position) // Remove the item from the list
        notifyItemRemoved(position) // Notify RecyclerView about the removed item
        notifyItemRangeChanged(position, myList.size) // Update remaining items
    }
    fun SetList(){

    }



}