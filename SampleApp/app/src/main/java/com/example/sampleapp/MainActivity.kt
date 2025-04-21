package com.example.sampleapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private val lc = "LifeCycle"
    val tag="click"


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("key", "Hello, World!")
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(lc, "2222222 onCreate() invoked <<<<<<<<<<<")
        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)
        setContentView(R.layout.act_main)
//        setContentView(R.layout.activity_constraint)

        findViewById<Button>(R.id.supabutton)
            .setOnClickListener {
                Log.d(tag, "User tapped the Supabutton")
            }

        Toast.makeText(this, "Key1: ${intent.getStringExtra("key1")} || " +
                "Key2: ${intent.getCharExtra("key2",'d')} || " +
                "Key3: ${intent.getBooleanExtra("key3",false)} || " +
                "Key4: ${intent.getIntExtra("key4",20)} || " +
                "Key5: ${intent.getStringExtra("key5")}",  Toast.LENGTH_LONG).show()


        findViewById<AppCompatButton>(R.id.buttonImage).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            Log.d(tag,"???????????? 2nd Act. >> FloatingActionButton Clicked")
            startActivity( Intent(this, ConstAct::class.java) )
//            finish()
        }

        findViewById<TextInputEditText>(R.id.editTextView).setOnClickListener{
            Log.d(tag,"???????????? 2nd Act. >> TextInputEditText view Clicked")
        }

        findViewById<ImageView>(R.id.imageV).setOnClickListener{
            Log.d(tag,"???????????? 2nd Act. >> Image View Clicked")
        }

        findViewById<Button>(R.id.buttonImage).setOnClickListener{
            Log.d(tag,"???????????? 2nd Act. >> Button with img Clicked")
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }
    }


    override fun onStart() { super.onStart(); Log.d(lc, "2222222 onStart() invoked <<<<<<<<<<<") }
    override fun onResume() { super.onResume(); Log.d(lc, "2222222 onResume() invoked <<<<<<<<<<<") }
    override fun onPause() { super.onPause(); Log.d(lc, "2222222 onPause() invoked <<<<<<<<<<<") }
    override fun onStop() { super.onStop(); Log.d(lc, "2222222 onStop() invoked <<<<<<<<<<<") }
    override fun onRestart() { super.onRestart(); Log.d(lc, "2222222 onRestart() invoked <<<<<<<<<<<") }
    override fun onDestroy() { super.onDestroy(); Log.d(lc, "2222222 onDestroy() invoked <<<<<<<<<<<") }
}


