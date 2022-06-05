package com.example.lab7

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lab7.databinding.ActivityMainBinding

lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Channel for all our notifications
        //If version of SDK is higher than Oreo version, we can create channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "MESSAGES",
                "MY_CHANNEL",
                NotificationManager.IMPORTANCE_HIGH)
            channel.description = "Channel for lab tasks' notifications"
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private val batteryReceiver = BatteryReceiver(supportFragmentManager)
    private val modeReceiver = ModeReceiver(supportFragmentManager)

    //ACTION_CAMERA_BUTTON will not work in android versions greater than Marshmallow version
    private val cameraReceiver = CameraReceiver()


    //All receivers will catch broadcast messages only if app is running
    override fun onStart() {
        super.onStart()
        //Register all receivers
        registerReceiver(batteryReceiver, IntentFilter(Intent.ACTION_BATTERY_LOW))
        registerReceiver(modeReceiver, IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED))
        registerReceiver(cameraReceiver, IntentFilter(Intent.ACTION_CAMERA_BUTTON))
    }

    override fun onStop() {
        super.onStop()
        //Unregister receivers
        unregisterReceiver(batteryReceiver)
        unregisterReceiver(modeReceiver)
        unregisterReceiver(cameraReceiver)
    }
}