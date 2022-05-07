package com.example.lab6

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class NumbersAdapter(private val inflater: LayoutInflater, var listener: OnItemClickListener?):
    ListAdapter<MainActivity.Item, NumbersAdapter.ViewHolder>(FriendsDiffCallback()) {

    interface OnItemClickListener {
        fun onItemClick(item: MainActivity.Item)
    }
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val number = view.findViewById<TextView>(R.id.tvNumber)
        private val layout = view.findViewById<ConstraintLayout>(R.id.constraintLayout)
        fun bind(item: MainActivity.Item){
            number.text = item.number.toString()
            layout.background.setTint(Color.parseColor(item.background))
            this.itemView.setOnClickListener {
                listener?.onItemClick(item)
            }
        }
    }

    //create new item of list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    //get link for item in list and return ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    //count difference between objects
    class FriendsDiffCallback: DiffUtil.ItemCallback<MainActivity.Item>(){
        //compare two objects
        override fun areItemsTheSame(
            oldItem: MainActivity.Item,
            newItem: MainActivity.Item
        ): Boolean = oldItem  == newItem

        //compare content of objects
        override fun areContentsTheSame(
            oldItem: MainActivity.Item,
            newItem: MainActivity.Item
        ): Boolean {
            return oldItem.number == newItem.number && oldItem.background == newItem.background
        }
    }

}