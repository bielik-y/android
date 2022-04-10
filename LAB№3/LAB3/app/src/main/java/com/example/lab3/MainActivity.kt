package com.example.lab3

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.lab3.contracts.CameraContract
import com.example.lab3.contracts.EmailContract
import com.example.lab3.databinding.ActivityMainBinding
import java.io.File


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var uri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide() //remove actionbar

        val imageLauncher = registerForActivityResult(CameraContract()){
            when(it.result) {
                true -> {
                    binding.ivSelfie.setImageURI(uri)
                    binding.tvImageHint.text = resources.getString(R.string.img_uploaded)
                    binding.btnSend.isEnabled = true
                }
                false -> Toast.makeText(this, "You left camera app", Toast.LENGTH_LONG).show()
            }
        }

        val emailLauncher = registerForActivityResult(EmailContract()){
            //email intent always return 0 as soon as sending starts or sending is canceled, so i can't check if email is send
            Toast.makeText(this, "You have left camera app", Toast.LENGTH_LONG).show()
        }

        binding.btnCamera.setOnClickListener {
            uri = createFile()
            imageLauncher.launch(CameraContract.Input(uri))
        }

        binding.btnSend.setOnClickListener {
            emailLauncher.launch(EmailContract.Input("КПП УИ-191 Белик", uri))
        }
    }

    private fun createFile(): Uri {
        val storageDir  = getExternalFilesDir(Environment.DIRECTORY_PICTURES) //photos remain private to this app only
        val file = File.createTempFile("selfie", ".jpg", storageDir) //create .jpg file in dir
        return FileProvider.getUriForFile(this, "com.example.lab3.provider", file)
    }
}
