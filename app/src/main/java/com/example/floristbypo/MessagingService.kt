package com.example.floristbypo

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.content.Context.NOTIFICATION_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import android.app.NotificationManager
import android.R
import android.app.NotificationChannel
import android.content.Context
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat



class MessagingService: FirebaseMessagingService() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
//        super.onMessageReceived(p0)
        val notificationBuilder = NotificationCompat.Builder(this, "channel_id")
            .setContentTitle(remoteMessage!!.notification?.title)
            .setContentText(remoteMessage.notification?.body)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setStyle(NotificationCompat.BigTextStyle())
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            .setSmallIcon(R.drawable.ic_notification_overlay)
            .setAutoCancel(true)

        val serviceChannel = NotificationChannel("channel_id", "Florist Msg", NotificationManager.IMPORTANCE_HIGH)
        serviceChannel.enableLights(true)
        serviceChannel.enableVibration(true)
        serviceChannel.lightColor = Color.GREEN
        serviceChannel.vibrationPattern = longArrayOf(300, 300, 300, 300, 300)
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(serviceChannel)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(0, notificationBuilder.build())
    }

    override fun onNewToken(p0: String?) {
        super.onNewToken(p0)
    }
}