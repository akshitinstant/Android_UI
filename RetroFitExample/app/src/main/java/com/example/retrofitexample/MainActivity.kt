package com.example.retrofitexample

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

        val myViewModel: MyViewModel by viewModels()

        lifecycleScope.launch {
            try {

                val catList = RetroFitHelper.createRetrofit("https://api.thecatapi.com/v1/").getCats(limit = 10, mime="gif")

                catList.forEach {
                    Log.d("retro8", "Cat ID: ${it.id}, URL: ${it.url}")
                }
            } catch (e: Exception) {
                Log.e("retro8", "Failed to load cats: ${e.message}")
            }
        }


    }
}