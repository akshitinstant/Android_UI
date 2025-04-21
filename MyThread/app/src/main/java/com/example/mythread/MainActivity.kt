package com.example.mythread

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mythread.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    var number: Int = 1
    lateinit var bind: ActivityMainBinding
    var handler = Handler(Looper.getMainLooper())
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

        /*

                Thread{
                    Log.d("thread","BACKGROUND Thread: ${Thread.currentThread().name}")     // 2->Thread-2
                    handler.post { Log.d("thread","MAIN from BACKGROUND: ${Thread.currentThread().name}") }     // 3-> MAIN
                }.start()
                Log.d("thread","MAIN Thread: ${Thread.currentThread().name}")       // 1->MAIN
        */


    }

    fun increment(view: View) {
        Thread {
            handler.post {
                bind.text.text =
                    number++.toString()        //app. will crash if tried to update UI from background thread without using handler.post
            }
        }.start()
    }


    fun loop(view: View) {

        Thread {
            for (i in 1..1000000000L) {
            }
        }.start()

    //        thread(start=true){
    //            for (i in 1..1000000000L){}     // making main thread busy
    //        }

    }
}


