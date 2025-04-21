package com.example.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

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

        Log.d("frag", "Activity -> onCreate()")

        var bt1 = findViewById<Button>(R.id.btn1)
        var bt2 = findViewById<Button>(R.id.btn2)
        var bt3 = findViewById<Button>(R.id.btn3)

        bt1.setOnClickListener {
            var fragManager: FragmentManager = supportFragmentManager
            var fragTrxn: FragmentTransaction = fragManager.beginTransaction()
            fragTrxn.add(R.id.conatiner, MyFragment())
            fragTrxn.addToBackStack(null)
            fragTrxn.commit()
        }

        bt2.setOnClickListener {
            var fragManger: FragmentManager = supportFragmentManager
            var fragTrxn: FragmentTransaction = fragManger.beginTransaction()
            fragTrxn.add(R.id.conatiner, MyFragment2())
            fragTrxn.addToBackStack(null)
            fragTrxn.commit()
        }

        bt3.setOnClickListener {
            var fragManger: FragmentManager = supportFragmentManager
            fragManger.popBackStack()
        }


    }

    override fun onDestroy() { super.onDestroy(); Log.d("frag","Activity -> onDestroy()") }
    override fun onStart() { super.onStart(); Log.d("frag","Activity -> onStart()") }
    override fun onPause() { super.onPause(); Log.d("frag","Activity -> onPause()") }
    override fun onStop() { super.onStop(); Log.d("frag","Activity -> onStop()") }
    override fun onResume() { super.onResume(); Log.d("frag","Activity -> onResume()") }
    override fun onRestart() { super.onRestart(); Log.d("frag","Activity -> onRestart()") }

}