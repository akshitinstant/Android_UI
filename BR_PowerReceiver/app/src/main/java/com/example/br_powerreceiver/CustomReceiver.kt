package com.example.br_powerreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class CustomReceiver : BroadcastReceiver() {
private  val ACTION_CUSTOM_BROADCAST="com.example.br_powerreceiver.ACTION_CUSTOM_BROADCAST"
    override fun onReceive(context: Context?, intent: Intent?) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
//        val intentAction = intent?.action
        when(intent?.action){
            Intent.ACTION_POWER_CONNECTED -> Toast.makeText(context,"Power Connected", Toast.LENGTH_SHORT).show()
            Intent.ACTION_POWER_DISCONNECTED -> Toast.makeText(context, "Power Disconnected", Toast.LENGTH_SHORT).show()
            Intent.ACTION_AIRPLANE_MODE_CHANGED -> Toast.makeText(context, "AirPlane Mode Changed", Toast.LENGTH_SHORT).show()
            ACTION_CUSTOM_BROADCAST -> Toast.makeText(context, "Custom Broadcast Received", Toast.LENGTH_SHORT).show()
        }
    }
}