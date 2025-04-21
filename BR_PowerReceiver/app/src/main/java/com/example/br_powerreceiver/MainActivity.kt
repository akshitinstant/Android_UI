package com.example.br_powerreceiver

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class MainActivity : AppCompatActivity() {

    private val customReceiver = CustomReceiver()
    private  val ACTION_CUSTOM_BROADCAST="com.example.br_powerreceiver.ACTION_CUSTOM_BROADCAST"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        var filter= IntentFilter()
        filter.addAction(Intent.ACTION_POWER_CONNECTED)
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)

        registerReceiver(customReceiver, filter)

//        LocalBroadcastManager.getInstance(this).registerReceiver(customReceiver, IntentFilter(ACTION_CUSTOM_BROADCAST))
    }

    override fun onDestroy() {
        super.onDestroy()
//        unregisterReceiver(customReceiver)
        LocalBroadcastManager.getInstance(this).unregisterReceiver(customReceiver)
    }

    fun sendCustomBroadcast(view: View) {
        var customBroadcastIntent= Intent(ACTION_CUSTOM_BROADCAST)
//        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent)
        customBroadcastIntent.putExtra("key", "value")
        sendBroadcast(customBroadcastIntent)
    }
}