package com.example.permissions_runtime

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.CAMERA
import android.Manifest.permission.READ_SMS
import android.Manifest.permission.RECORD_AUDIO
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.permissions_runtime.databinding.ActivityMainBinding
import kotlin.jvm.java
import androidx.core.net.toUri

class MainActivity : AppCompatActivity() {


    val REQ_CODE = 300
    private val alarmManager by lazy {
        getSystemService(ALARM_SERVICE) as AlarmManager
    }
    lateinit var bind: ActivityMainBinding

    private val requestExactAlarmPermission =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S || alarmManager.canScheduleExactAlarms()) {
                setExactAlarm()
            } else {
                Toast.makeText(this, "Exact alarm permission not granted", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        bind.btn1.setOnClickListener {
            scheduleExactAlarm()
        }
    }

    fun askPermission(view: View) {
        val perm = arrayOf(ACCESS_FINE_LOCATION, READ_SMS, CAMERA, RECORD_AUDIO)
        if (perm.all { ActivityCompat.checkSelfPermission(this, it)== PackageManager.PERMISSION_GRANTED }) {
            Toast.makeText(this, "Permission already Granted!", Toast.LENGTH_SHORT).show()
        } else {
            ActivityCompat.requestPermissions(this, perm, REQ_CODE)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQ_CODE) {
                if (grantResults.all { it== PackageManager.PERMISSION_GRANTED }) {
                    Toast.makeText(this, "All Permission Granted Successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show()
                }

        }
    }




    fun scheduleExactAlarm() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !alarmManager.canScheduleExactAlarms()) {
            val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM).apply {
                data = "package:$packageName".toUri()
            }
            requestExactAlarmPermission.launch(intent)
        } else {
            setExactAlarm()
        }
    }

    fun setExactAlarm() {
        val triggerAt = System.currentTimeMillis() + 10_000L
        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAt, pendingIntent)
        Toast.makeText(this, "Alarm set for 10s from now", Toast.LENGTH_SHORT).show()
    }

    fun stopAlarmTone(view: View) {
        AlarmReceiver.ringtone?.stop()
        Toast.makeText(this, "Alarm Stopped", Toast.LENGTH_SHORT).show()
    }

}

class AlarmReceiver : BroadcastReceiver() {
    companion object {
        var ringtone: Ringtone? = null
    }

    override fun onReceive(context: Context, intent: Intent?) {
        Toast.makeText(context, "Alarm Triggered!", Toast.LENGTH_LONG).show()

        val alarmUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
            ?: RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        ringtone = RingtoneManager.getRingtone(context, alarmUri)
        ringtone?.play()
    }
}