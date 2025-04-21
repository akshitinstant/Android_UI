package com.example.frag_lifecycle_child

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("frag","Activity -> onCreate()")
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var fragManager=supportFragmentManager

        fragManager.beginTransaction().add(R.id.conatiner, MyFrag1()).commit()


        findViewById<Button>(R.id.btn1).setOnClickListener {
            fragManager.beginTransaction().add(R.id.conatiner, MyFrag1()).addToBackStack(null).commit() }

        findViewById<Button>(R.id.btn2).setOnClickListener {
            fragManager.beginTransaction().add(R.id.conatiner, Myfrag2()).addToBackStack(null).commit() }

        findViewById<Button>(R.id.btn3).setOnClickListener {
            fragManager.beginTransaction().add(R.id.conatiner, MyFrag3()).addToBackStack(null).commit() }

        findViewById<Button>(R.id.fidFragById).setOnClickListener {
            val frag =fragManager.findFragmentById(R.id.conatiner)
            if(frag!=null) {
                Toast.makeText(this,"Current Fragment: ${frag.javaClass.simpleName}",Toast.LENGTH_SHORT).show()
            }

//            val firstFragment = fragManager.fragments.lastOrNull()
//            Toast.makeText(this, "First Fragment: ${firstFragment?.javaClass?.simpleName}", Toast.LENGTH_SHORT).show()

//            val allFragments = fragManager.fragments
//            allFragments.forEach { fr ->
//                Toast.makeText(this, "Fragments: ${fr.javaClass.simpleName}", Toast.LENGTH_SHORT).show()
//            }

        }

    }


    override fun onDestroy() { super.onDestroy(); Log.d("frag","Activity -> onDestroy()") }
    override fun onStart() { super.onStart(); Log.d("frag","Activity -> onStart()") }
    override fun onPause() { super.onPause(); Log.d("frag","Activity -> onPause()") }
    override fun onStop() { super.onStop(); Log.d("frag","Activity -> onStop()") }
    override fun onResume() { super.onResume(); Log.d("frag","Activity -> onResume()") }
    override fun onRestart() { super.onRestart(); Log.d("frag","Activity -> onRestart()") }


}