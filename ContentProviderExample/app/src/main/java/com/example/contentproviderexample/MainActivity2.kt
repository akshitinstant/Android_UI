package com.example.contentproviderexample

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val values = ContentValues().apply {
            put(MyContract.COLUMN_NAME, "Test Item")
        }
        contentResolver.insert(MyContract.CONTENT_URI, values)
//        val resolver = contentResolver
        val cursor = contentResolver.query(MyContract.CONTENT_URI, null, null, null, null)

        cursor?.use {
            while (it.moveToNext()) {
                val name = it.getString(it.getColumnIndexOrThrow(MyContract.COLUMN_NAME))
                Log.d("con10","Item name: ${name}")
            }
        }

    }
}