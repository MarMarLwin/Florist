package com.example.floristbypo

import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MessagingService: FirebaseMessagingService() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
//        super.onMessageReceived(p0)
        val intent= Intent(this,MainActivity::class.java)
        intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
        val contentIntent=PendingIntent.getActivity(this,0,intent,0)
           val notificationBuilder = NotificationCompat.Builder(this, "channel_id")
            .setContentTitle(remoteMessage!!.notification?.title)
            .setContentText(remoteMessage.notification?.body)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setStyle(NotificationCompat.BigTextStyle())
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            .setSmallIcon(R.drawable.ic_notification_overlay)
            .setAutoCancel(false)
            .addAction(R.drawable.ic_input_add,"Show",contentIntent)

        val notification=notificationBuilder.setContentIntent(contentIntent).build()

        val serviceChannel = NotificationChannel("channel_id", "Florist Msg", NotificationManager.IMPORTANCE_HIGH)
        serviceChannel.enableLights(true)
        serviceChannel.enableVibration(true)
        serviceChannel.lightColor = Color.GREEN
        serviceChannel.vibrationPattern = longArrayOf(300, 300, 300, 300, 300)
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(serviceChannel)

//        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        manager.notify(0, notificationBuilder.build())
        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.ECLAIR)
            startForeground(1,notification)
    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }
}