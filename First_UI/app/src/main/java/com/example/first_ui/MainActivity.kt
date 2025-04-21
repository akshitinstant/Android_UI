package com.example.first_ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.ui_relative)

//        val intentObj=Intent()
//        intentObj.action="custom_action"
//        intent.putExtra("strkey","Welcome intent")
//        intent.putExtra("intkey",6776)
//        intent.setPackage("com.example.sampleapp")
//        startActivity(intentObj)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.relativeui)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}