package com.example.lab7

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.FragmentManager

class ModeReceiver(private val manager: FragmentManager): BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
            val text = if(intent.extras?.getBoolean("state") == true) {
                "Airplane Mode is on " + String(Character.toChars(0x1F910))
            } else
                "Airplane Mode is turned off " + String(Character.toChars(0x1F609))
            //Show dialog
            val dialog = BasicDialog("Airplane Mode info", "$text\nYou will also get notification.")
            dialog.show(manager, "dlg")
            //Show notification
            val builder = NotificationCompat.Builder(context, "MESSAGES")
                .setSmallIcon(R.drawable.airplane_mode)
                .setContentTitle("AIRPLANE MODE NOTIFICATION")
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            NotificationManagerCompat.from(context).apply {
                this.notify(2, builder.build())
            }
        }
    }
}