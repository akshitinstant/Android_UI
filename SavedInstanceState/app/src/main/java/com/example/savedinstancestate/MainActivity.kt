package com.example.savedinstancestate

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    var counter: Int = 0

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        Log.d("inc" , "SSSSave")
        outState.putInt("counter", ++counter)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        Log.d("inc" , "RRRRestore")
        val view = findViewById<TextView>(R.id.disp)
        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt("counter")
            view.text = "Visited $counter times."
        } else {
            view.text = "1st Visit"
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        Log.d("inc" , "CCCreate")
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        val view = findViewById<TextView>(R.id.disp)
//        if (savedInstanceState != null) {
//            counter = savedInstanceState.getInt("counter")
//            view.text = "Visited $counter times."
//        } else {
//            view.text = "1st Visit"
//        }

    }

    override fun onStart() {
        super.onStart()
        Log.d("inc" , "SSSStart")
    }

    override fun onResume() {
        super.onResume()

        Log.d("inc" , "RRRRResume")
    }

    override fun onRestart() {
        super.onRestart()

        Log.d("inc" , "RRRRRestart")
    }

    override fun onPause() {
        super.onPause()
        Log.d("inc" , "PPPPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("inc" , "SSSStop")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("inc" , "DDDDestroy")
    }

}