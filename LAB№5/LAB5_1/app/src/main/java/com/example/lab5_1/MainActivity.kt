package com.example.lab5_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.lab5_1.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

private lateinit var binding: ActivityMainBinding
private val list = listOf(FragmentGallery.newInstance(), FragmentProfile.newInstance(), FragmentFriends.newInstance())

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = MyAdapter(this, list)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, pos ->
            tab.text = when (pos) {
                0 -> "GALLERY"
                1 -> "PROFILE"
                2 -> "FRIENDS"
                else -> ""
            }
        }.attach()
    }

    class MyAdapter(activity: AppCompatActivity, private val fragments: List<Fragment>): FragmentStateAdapter(activity) {
        override fun getItemCount() = fragments.size
        override fun createFragment(position: Int) = fragments[position]
    }
}