package com.example.coordinatelayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.NestedScrollView
import com.example.coordinatelayout.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var bind: ActivityMainBinding
    lateinit var extendedFab: ExtendedFloatingActionButton
    lateinit var nestedScrollView: NestedScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        bind= ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        extendedFab=bind.extendedFab
        nestedScrollView=bind.nestedScrollView

        nestedScrollView.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (scrollY > oldScrollY)
                extendedFab.shrink()
            else if(scrollY < oldScrollY)
                extendedFab.extend()
        }
    }
}