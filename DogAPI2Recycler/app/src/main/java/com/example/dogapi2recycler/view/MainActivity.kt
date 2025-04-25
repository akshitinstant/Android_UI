package com.example.dogapi2recycler.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.dogapi2recycler.view.GridActivity
import com.example.dogapi2recycler.view.ListActivity
import com.example.dogapi2recycler.R
import com.example.dogapi2recycler.databinding.ActivityMainBinding
import com.example.dogapi2recycler.model.User
import com.example.dogapi2recycler.viewmodel.MyViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var bind: ActivityMainBinding
    val myVM: MyViewModel by viewModels()

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

        val pref = getSharedPreferences("appSharedPref", MODE_PRIVATE)
        val name = pref.getString("name", "?")
        bind.profileBtn.text = name?.firstOrNull().toString().uppercase()
    }

    fun toListAct(view: View) {
        startActivity(Intent(this, ListActivity::class.java))
    }

    fun toGridAct(view: View) {
        startActivity(Intent(this, GridActivity::class.java))
    }

    fun logOutUser(view: View) {
        val myDialog = LogoutDialogFragment()
        myDialog.show(supportFragmentManager, "LogoutDialog")
    }

    fun userList(view: View) {
        startActivity(Intent(this, UserListActivity::class.java))
    }


}