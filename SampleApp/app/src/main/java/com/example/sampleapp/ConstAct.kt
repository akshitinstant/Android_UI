package com.example.sampleapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ConstAct : AppCompatActivity() {
    val tag = "click"
    val lc = "LifeCycle"
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_const)
        setContentView(R.layout.chain)
//        setContentView(R.layout.edittext_card)
        Log.d(lc, "11111111 onCreate() invoked <<<<<<<<<<<")


// TODO
//        val receivedStr=intentObj.getStringExtra("strkey")
//        val receivedInt=intentObj.getIntExtra("intkey",33)
//        Toast.makeText(this,"String: $receivedStr & Int: $receivedInt",Toast.LENGTH_LONG).show()


        findViewById<Button>(R.id.button2).setOnClickListener {
            Log.d(tag, "???????????? Button 2 Clicked")
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("key1", "Hello")
                putExtra("key2", 'h')
                putExtra("key3", true)
                putExtra("key4", 80)
            }
            startActivity(intent)

        }

        findViewById<Button>(R.id.button3).setOnClickListener {
            Log.d(tag, "???????????? Button 3 Clicked")
            val intent = Intent(Intent.ACTION_MAIN)
            intent.setPackage("com.google.android.youtube")
            startActivity(intent)
        }

        findViewById<Button>(R.id.button1).setOnClickListener {
            Log.d(tag, "???????????? Button 1 Clicked")
            val intent = Intent(Intent.ACTION_SEND).apply {
                /* type = "text/plain"
                 putExtra(Intent.EXTRA_EMAIL, arrayOf("test@example.com"))
                 putExtra(Intent.EXTRA_SUBJECT, "My Subject")
                 putExtra(Intent.EXTRA_TEXT, "Email content")*/
            }
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            startActivity(Intent.createChooser(intent, "Choose an Email client"))
        }

        findViewById<TextView>(R.id.textView1).setOnClickListener {
            Log.d(tag, "???????????? Text View 1 Clicked")
        }

        findViewById<View>(R.id.view1).setOnClickListener {
            Log.d(tag, "???????????? View 1 Clicked")
        }
// Modify the value at runtime
        val shortText = findViewById<TextView>(R.id.text1)
        shortText.setOnClickListener {
            shortText.text =
                getString(R.string.ModifiedTextOnClick)  // .text (give value as literal)
            Log.d(tag, "???????????? Text 1 Clicked")
        }

        val editText = findViewById<EditText>(R.id.editView)
        editText.setOnClickListener {
            editText.setText(R.string.ModifiedTextOnClick)          // .setText() (pass value as parameter)
            Log.d(tag, "???????????? Edit Text View Clicked")
        }

        val img2 = findViewById<ImageView>(R.id.image2)
        img2.setOnClickListener {
            img2.setImageResource(R.drawable.s_messagebot)      // Load the image from drawable, and replace
            Log.d(tag, "???????????? Image View 2 Clicked")
        }

        val img = findViewById<ImageView>(R.id.image1)
        img.setOnClickListener {
            img.setImageDrawable(img2.drawable)             // Reuses the already loaded drawable
            Log.d(tag, "???????????? Image View 1 Clicked")
        }
//        getDrawable(R.drawable.s_playstore)
//        ContextCompat.getDrawable(this,R.drawable.s_playstore)    //Todo

        val btn = findViewById<Button>(R.id.button)
        btn.setOnClickListener {
            btn.setText(R.string.ModifiedTextOnClick)
            Log.d(tag, "???????????? Aligned Button Clicked")
        }


        val value = findViewById<TextView>(R.id.value)
        findViewById<Button>(R.id.submit).setOnClickListener {
//            value.text=editText.text
//            value.append(editText.text)
            var intVal = editText.text.toString().toIntOrNull() ?: 0
            intVal += 10
            value.text = intVal.toString()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.chain)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    override fun onStart() {
        super.onStart(); Log.d(lc, "11111111 onStart() invoked <<<<<<<<<<<")
    }

    override fun onResume() {
        super.onResume(); Log.d(lc, "11111111 onResume() invoked <<<<<<<<<<<")
    }

    override fun onPause() {
        super.onPause(); Log.d(lc, "11111111 onPause() invoked <<<<<<<<<<<")
    }

    override fun onStop() {
        super.onStop(); Log.d(lc, "11111111 onStop() invoked <<<<<<<<<<<")
    }

    override fun onRestart() {
        super.onRestart(); Log.d(lc, "11111111 onRestart() invoked <<<<<<<<<<<")
    }

    override fun onDestroy() {
        Log.d(lc, "11111111 onDestroy() invoked <<<<<<<<<<<"); super.onDestroy(); Log.d(
            lc,
            "11111111 onDestroy() invoked <<<<<<<<<<<"
        )
    }

}
