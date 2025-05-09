// Exception handling using try-Catch and CoroutineExceptionHandler

package com.example.coroutinebasic

import ViewModel.MyViewModel
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope

class MainActivity4 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main4)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        val myViewModel: MyViewModel by viewModels()
        val myViewModel = ViewModelProvider(this)[MyViewModel::class.java]


        val exceptionHandler = CoroutineExceptionHandler { _, exception ->
            println("CoroutineExceptionHandler caught: ${exception.message}")
        }

        val coroutine = CoroutineScope(Dispatchers.Default + exceptionHandler)

        coroutine.launch{
            println("Starting coroutine...")
            throw RuntimeException("Aaaaa! Got an Exception")
        }
        Thread.sleep(1000)
        println("App is still running!")


//        val coroutine=CoroutineScope(Dispatchers.Default)
//        coroutine.launch{
//            try {
//                println("Starting coroutine...")
//                throw RuntimeException("Aaaaa! Got an Exception")
//            } catch(e:Exception){
//                println("Caught inside try-catch: ${e.message}")
//            }
//        }
//        Thread.sleep(1000)
//        println("App is still running!")
    }
}