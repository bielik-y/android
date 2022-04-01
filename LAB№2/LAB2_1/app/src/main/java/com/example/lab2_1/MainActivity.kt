package com.example.lab2_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lab2_1.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding // delayed initialization

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide() //remove actionbar with title of app


        newGame()
    }

    private fun newGame() {
        binding.etAnswerField.text?.clear() // clear the input field
        val word = getWord() //get random word
        val mixed = word.toCharArray().let {
            it.shuffle()
            it.concatToString()
        }
        binding.tvScrambledWord.text = mixed

        binding.btnCheck.setOnClickListener {
            val input = binding.etAnswerField.text.toString()

            if (input.equals(word)) {
                Toast.makeText(this, "CORRECT!", Toast.LENGTH_SHORT).show()
                newGame()
            }
            else
                Toast.makeText(this, "INCORRECT!", Toast.LENGTH_SHORT).show()
        }

        binding.btnSkip.setOnClickListener {
            newGame()
        }
    }

    private fun getWord(): String {
        val array = resources.getStringArray(R.array.words);
        return array[Random.nextInt(array.size)]
    }
}