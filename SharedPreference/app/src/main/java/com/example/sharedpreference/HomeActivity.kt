package com.example.sharedpreference

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.content.edit
import androidx.core.net.toUri

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun logout(view: View) {
        val pref:SharedPreferences=getSharedPreferences("login", MODE_PRIVATE)
        pref.edit() {
            putBoolean("flag", false)
            remove("user")
        }

        startActivity(Intent(this, MainActivity::class.java))
        finishAffinity()
    }

    fun deepLink(view: View) {
        val pref: SharedPreferences=getSharedPreferences("login",MODE_PRIVATE)
        val user=pref.getString("user","User!")

        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = "myapp://mainfragment?id=101&uname=$user".toUri()
//            setPackage("com.example.navigation") // Optional: To target your specific app
        }
        startActivity(intent)

    }
}