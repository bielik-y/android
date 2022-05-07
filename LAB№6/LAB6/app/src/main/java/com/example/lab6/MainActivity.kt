package com.example.lab6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.lab6.databinding.ActivityMainBinding
import kotlin.random.Random

private val array: MutableList<MainActivity.Item> = mutableListOf()
private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        for (i in 1..28)
            array.add(getRandomItem())

        val adapter = NumbersAdapter(layoutInflater, null)
        adapter.listener = object :NumbersAdapter.OnItemClickListener{
            override fun onItemClick(item: Item) {
                val dialog = Dialog(item.number)
                dialog.show(supportFragmentManager, "dlg")
            }

        }

        binding.rvList.adapter = adapter
        binding.rvList.layoutManager = GridLayoutManager(this, 4, GridLayoutManager.VERTICAL,false)
        adapter.submitList(array.toList())
    }

    data class Item (val number: Int, val background: String)
}

private fun getRandomNumber(): Int {
    return Random.nextInt(100)
}

private fun getRandomColor(): String {
    val letters = "0123456789abcdef"
    var color = "#"
    for (i in 0..5) {
        color += letters[Random.nextInt(letters.length)]
    }
    return color

}

private fun getRandomItem(): MainActivity.Item {
    return MainActivity.Item(getRandomNumber(), getRandomColor())
}