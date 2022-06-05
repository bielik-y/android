package com.example.lab7

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class CameraReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action == Intent.ACTION_CAMERA_BUTTON) {
            val text = "You have opened camera app" + String(Character.toChars(0x1F4F7))
            //Show notification
            val builder = NotificationCompat.Builder(context, "MESSAGES")
                .setSmallIcon(R.drawable.camera_icon)
                .setContentTitle("CAMERA BUTTON NOTIFICATION")
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            NotificationManagerCompat.from(context).apply {
                this.notify(3, builder.build())
            }
        }
    }
}