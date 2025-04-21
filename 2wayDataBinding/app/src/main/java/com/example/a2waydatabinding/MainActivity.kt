package com.example.a2waydatabinding

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.a2waydatabinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val myViewModels: MyViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        myViewModel = ViewModelProvider(this)[MyViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.myViewModel = myViewModels


        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


//  For Delay......
//        Handler(Looper.getMainLooper()).postDelayed({
//            myViewModels.screenState.name = "cadbhd"
//            println("dcsbcjkd")
//        }, 5000)

    }
}