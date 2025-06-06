package com.example.launchmodes

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


    fun openAct2(view: View){
        val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent)
    }

    fun openAct3(view: View){
        val intent = Intent(this, MainActivity3::class.java)
        startActivity(intent)
    }

    fun openAct4(view: View){
        val intent = Intent(this, MainActivity4::class.java)
        startActivity(intent)
    }


}