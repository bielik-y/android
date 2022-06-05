package com.example.lab7

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.FragmentManager

class BatteryReceiver(private val manager: FragmentManager): BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BATTERY_LOW) {
            //Show dialog
            val dialog = BasicDialog("Battery info", "Your battery is low. You will also get notification")
            dialog.show(manager, "dlg")
            //Show notification
            val builder = NotificationCompat.Builder(context,"MESSAGES")
                .setSmallIcon(R.drawable.low_battery)
                .setContentTitle("LOW BATTERY NOTIFICATION")
                .setContentText("I hope your city has electricity to charge your phone" + String(Character.toChars(0x26A1)))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            NotificationManagerCompat.from(context).apply {
                this.notify(1, builder.build())
            }
        }
    }

}