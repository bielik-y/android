package com.example.lab4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class FriendsAdapter(private val inflater: LayoutInflater, var listener: OnItemClickListener?):
    ListAdapter<MainActivity.Friend, FriendsAdapter.ViewHolder>(FriendsDiffCallback()) {

    interface OnItemClickListener{
        fun onDeleteClick(position: Int)
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val avatar = view.findViewById<ImageView>(R.id.ivAvatar)
        private val username = view.findViewById<TextView>(R.id.tvNickname)
        private val button = view.findViewById<ImageButton>(R.id.ibDelete)

        fun bind(friend: MainActivity.Friend){
            avatar.setImageResource(friend.avatar)
            username.text = friend.username
            button.setOnClickListener {
                listener?.onDeleteClick(currentList.indexOf(friend))
            }
        }
    }

    //create new item of list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.friends_item, parent, false)
        return ViewHolder(view)
    }

    //get link for item in list and return ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val friend = getItem(position)
        holder.bind(friend)
    }

    //count difference between objects
    class FriendsDiffCallback: DiffUtil.ItemCallback<MainActivity.Friend>(){
        //compare two objects
        override fun areItemsTheSame(
            oldItem: MainActivity.Friend,
            newItem: MainActivity.Friend
        ): Boolean = oldItem  == newItem

        //compare content of objects
        override fun areContentsTheSame(
            oldItem: MainActivity.Friend,
            newItem: MainActivity.Friend
        ): Boolean {
            return oldItem.username == newItem.username && oldItem.avatar == newItem.avatar
        }
    }
}