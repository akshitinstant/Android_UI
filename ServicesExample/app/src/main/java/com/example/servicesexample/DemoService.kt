package com.example.servicesexample

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import kotlin.concurrent.thread

class DemoService : Service() {

    override fun onCreate() {
        super.onCreate()
        Log.d("ser6", "onCreate()")
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("ser6", "onStartCommand()")

        thread(start = true) {
            while (true) {
                Log.d("ser6", "Loading Message")
                Thread.sleep(1000)
            }
        }
        startLoggerForegroundService()
        return super.onStartCommand(intent, flags, startId)
    }

    fun startLoggerForegroundService() {
        createNotificationChannel()
        val notification = createNotification()
        startForeground(111, notification)
    }

    fun getPendingIntent(): PendingIntent {
        val intent = Intent(this, MainActivity::class.java)
        return PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    }

    fun createNotificationChannel(): NotificationChannel {
        val channel = NotificationChannel("id", "MyName", NotificationManager.IMPORTANCE_DEFAULT)
        val notificationManager =
            ContextCompat.getSystemService(this, NotificationManager::class.java)
        notificationManager?.createNotificationChannel(channel)
        return channel
    }

    fun createNotification(): Notification {
        val notification = NotificationCompat.Builder(this, "id")
            .setContentText("Foreground Services running")
            .setContentTitle("myName")
            .setContentIntent(getPendingIntent())
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setOngoing(true)
            .build()
        return notification
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ser6", "onDestroy()")
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}