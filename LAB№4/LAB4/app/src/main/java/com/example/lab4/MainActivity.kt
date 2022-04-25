package com.example.lab4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab4.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {


    private val friends: MutableList<Friend> = mutableListOf()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = FriendsAdapter(layoutInflater, null)
        adapter.listener = object: FriendsAdapter.OnItemClickListener {
            override fun onDeleteClick(position: Int) {
                friends.removeAt(position)
                adapter.submitList(friends.toList())
            }
        }

        binding.rvFriends.adapter = adapter
        binding.rvFriends.layoutManager = LinearLayoutManager(this)
        adapter.submitList(null)

        binding.btnAdd.setOnClickListener{
            val friend = Friend(getRandomName(), getRandomAvatar())
            friends.add(friend)
            adapter.submitList(friends.toList())
        }

        binding.btnRemove.setOnClickListener {
            if (friends.isNotEmpty()) {
                friends.removeLast()
                adapter.submitList(friends.toList())
            }
            else
                Toast.makeText(this, "You don't have any friend", Toast.LENGTH_SHORT).show()
        }

        binding.btnClear.setOnClickListener {
            friends.clear()
            adapter.submitList(friends.toList())
        }


    }

    data class Friend(val username: String, val avatar: Int)

    private fun getRandomName(): String {
        val array = resources.getStringArray(R.array.usernames);
        return array[Random.nextInt(array.size)]
    }

    private fun getRandomAvatar(): Int {
        val array = arrayListOf<Int>(R.drawable.avatar1,
            R.drawable.avatar2,
            R.drawable.avatar3,
            R.drawable.avatar4,
            R.drawable.avatar5,
            R.drawable.avatar6)
        return array[Random.nextInt(array.size)]
    }


}