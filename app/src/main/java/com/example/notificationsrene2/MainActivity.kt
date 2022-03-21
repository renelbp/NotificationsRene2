package com.example.notificationsrene2

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.notificationsrene2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private val NOTIF_CHA_ID1: String = " food service"
    private val NOTIF_CHA_ID2: String = "Phone App"

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val notificationChannel1 =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel(
                    NOTIF_CHA_ID1, "NOTIFICATION_CHANNEL_NAME", NotificationManager.IMPORTANCE_HIGH
                )
            } else {
                TODO()
            }
        notificationChannel1.enableLights(true)
        notificationChannel1.lightColor = Color.RED
        notificationChannel1.enableVibration(true)
        notificationChannel1.vibrationPattern =
            longArrayOf(0, 10, 200, 500, 700, 1000, 300, 200, 50, 10)

        notificationManager.createNotificationChannel(notificationChannel1)



        val notificationIntent1 = Intent(this, MainActivity2::class.java)
        notificationIntent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        //NOTIFICATION MESSAGE WILL GET AT NOTIFICATION VIEW
        notificationIntent1.putExtra("message", "this is a notification message")
        val pendingIntent = PendingIntent.getActivity(
            this, 0, notificationIntent1, PendingIntent.FLAG_UPDATE_CURRENT
        )
        val notification1 = getNotification(
            pendingIntent, NotificationCompat.PRIORITY_LOW,
            "Food Service", "Your Food Is Ready", R.drawable.ic_fast_food, NOTIF_CHA_ID1)




        val notificationIntent2 = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"))
        val pendingIntent2 = PendingIntent.getActivity(
            this, 0, notificationIntent2,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val notification2 = getNotification(pendingIntent2, NotificationCompat.PRIORITY_LOW,
            "Players Are Ready","Join Now", R.drawable.ic_baseline_videogame_asset_24,NOTIF_CHA_ID1)

        val notificationChannel2 =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel(
                    NOTIF_CHA_ID2, "NOTIFICATION_CHANNEL_NAME", NotificationManager.IMPORTANCE_HIGH
                )
            } else {
                TODO()
            }
        notificationChannel2.enableLights(true)
        notificationChannel2.lightColor = Color.RED
        notificationChannel2.enableVibration(true)
        notificationChannel2.vibrationPattern =
            longArrayOf(0, 10, 200, 500, 700, 1000, 300, 200, 50, 10)

        notificationManager.createNotificationChannel(notificationChannel2)

        val notificationIntent3 = Intent(Intent.ACTION_DIAL , Uri.parse("tel:" + "+5218712179909"))
        val pendingIntent3 = PendingIntent.getActivity(
            this, 0, notificationIntent3,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val notification3 = getNotification(pendingIntent3, NotificationCompat.PRIORITY_HIGH,
            "Phone","Missed call from: 5218712179909", R.drawable.ic_baseline_phone_missed_24,NOTIF_CHA_ID2)



        with(NotificationManagerCompat.from(this)) {
            //Notification id is an unique inv for each notification that you must define
            notify(23, notification1)
            notify(24,notification2)
            notify (25,notification3)
        }


    }

    private fun getNotification(
        pendindIntent: PendingIntent,
        priority: Int,
        title: String,
        content: String,
        icon: Int,
        channel_id: String
    ): Notification {
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(this, channel_id)
            .setSmallIcon(icon)
            .setContentTitle(title)
            .setContentText(content)
            .setAutoCancel(true)
            .setPriority(priority)
        builder.setContentIntent(pendindIntent)
        return builder.build()

    }
}