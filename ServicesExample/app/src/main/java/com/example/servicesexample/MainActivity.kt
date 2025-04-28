package com.example.servicesexample

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.Manifest
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.servicesexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var bind: ActivityMainBinding
    lateinit var myIntent: Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        bind= ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        myIntent= Intent(this, DemoService::class.java)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), 200)

        bind.b1.setOnClickListener {
//            startService(myIntent)        //background service
            startForegroundService(myIntent)        //foreground service
        }

        bind.b2.setOnClickListener {
            stopService(myIntent)
        }
    }
}