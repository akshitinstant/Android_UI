package com.example.roomdb.view

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.example.roomdb.R
import com.example.roomdb.databinding.ActivityMainBinding
import com.example.roomdb.db.AppDB
import com.example.roomdb.model.Subject
import com.example.roomdb.model.User
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var db: AppDB
    lateinit var bind: ActivityMainBinding
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

        bind.text.movementMethod = android.text.method.ScrollingMovementMethod()

        db = Room.databaseBuilder(this, AppDB::class.java, "MyDB").build()

        MainScope().launch {
            db.userDAO().insertUser(User(name = "John", mobile = "989098909"))
//            db.subjectDAO().insertSubject(Subject(subName = "Physics", subCode = "PHY25"))
        }
    }

    fun getUserData(view: View) {
        db.userDAO().getUser().observe(this) {
//                Log.d("db0", it.toString())
            var str=""
            it.forEach { str=str+"${it.id} || ${it.name} || ${it.mobile}\n" }
            bind.text.text=str
        }
    }

    fun getSubjectData(view: View) {
        db.subjectDAO().getSubject().observe(this) {
            bind.text.text = it.joinToString(separator = "\n") { "${it.subId} || ${it.subName} || ${it.subCode}" }
        }
    }

    fun getMultiMap(view: View) {
        db.userDAO().loadUserAndSubjectName().observe(this){
            val display = buildString {
                for ((user, subject) in it) {
                    append("${user.name}:\n")
                    for (sub in subject) {
                        append(" - ${sub.subName}\n")
                    }
                    append("\n")
                }
            }
            bind.text.text = display
        }
    }
}