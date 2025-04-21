package com.example.architecture.views

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.architecture.R
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.MutableCreationExtras
import com.example.architecture.model.User
//import com.example.architecture.views.MyViewModel2.Companion.MY_USER_KEY
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch

class ActMain : AppCompatActivity() {
    private lateinit var myViewModel: MyViewModel
//    private lateinit var myViewModel: MyViewModel2
    lateinit var btn: Button
    var count: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


//        private val  myViewModel: MyViewModel2 by viewModels()        // property


//  ------- without view model ----------
//        btn=findViewById<Button>(R.id.btn)
//        btn.setOnClickListener { increment(R.id.main)
//            btn.text=count.toString()}


//  --------- With View Model ------------
        val factory = MyViewModelFactory(30)
        myViewModel = ViewModelProvider(this, factory)[MyViewModel::class.java]
        btn = findViewById<Button>(R.id.btn)
        btn.text = myViewModel.count.toString()


//  ---------- creationExtras ---------------
//        val user = User()
//        val extras = MutableCreationExtras().apply {
//            set(MY_USER_KEY, user)
//        }
//        myViewModel = ViewModelProvider(this, MyViewModel2.Factory, extras)[MyViewModel2::class.java]
        //TODO: try creating a CustomViewModelProvider by extending ViewModelProvider

    }

    fun increment(view: View) {
        myViewModel.increment()
        btn.text = myViewModel.count.toString()
    }

}