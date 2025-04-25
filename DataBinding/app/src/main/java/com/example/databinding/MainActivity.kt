package com.example.databinding

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.databinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var clickEvent : ClickHandlingInnerClass
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

//        binding= ActivityMainBinding.inflate(getLayoutInflater())
//        setContentView(binding.root)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.btnV.setOnClickListener {
            binding.textV.text = getString(R.string.data_binding_done)
            val qouteObj= Quote("My quote","My Name")
            binding.quote=qouteObj
        }


        clickEvent = ClickHandlingInnerClass(this)
//        binding.setClickHandler(clickEvent)       //setClickHandler() is created by dataBinding internally
        binding.clickHandler=clickEvent

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }



    class ClickHandlingInnerClass(val context: Context){

        fun onEnrollButtonClick(){
            Toast.makeText(context, "Enrolled", Toast.LENGTH_SHORT).show()
        }
        fun onCancelButtonClick(view : View){
            Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show()
        }

    }

}