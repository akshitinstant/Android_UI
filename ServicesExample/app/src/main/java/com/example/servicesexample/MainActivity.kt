package com.example.servicesexample

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.Manifest
import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.os.IBinder
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.servicesexample.databinding.ActivityMainBinding
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {
    lateinit var bind: ActivityMainBinding
    lateinit var myIntent: Intent
    private var demoService: DemoService? = null
    private var isBound = false

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as DemoService.LocalBinder
            demoService = binder.getServices()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            demoService = null
            isBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        myIntent = Intent(this, DemoService::class.java)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.POST_NOTIFICATIONS),
            200
        )

        bind.b1.setOnClickListener {
//            startService(myIntent)        //background service
            startForegroundService(myIntent)        //foreground service

        }

        bind.b2.setOnClickListener {
            stopService(myIntent)
        }
    }

    override fun onStart() {
        super.onStart()
        Intent(this, DemoService::class.java).also { intemt ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
        EventBus.getDefault().register(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onDownloadedCompleted(event: DownloadCompleteEvent) {
        Toast.makeText(this, "Download: ${event.fileName}", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        EventBus.getDefault().unregister(this)
        if (isBound) {
            unbindService(connection)
        }
        super.onStop()
    }

    fun showRandomNumber() {
        if (isBound) {
            val num = demoService?.getRandomNumber()
            Toast.makeText(this, "Random Number: $num", Toast.LENGTH_SHORT).show()
        }
    }

}