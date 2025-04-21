package com.example.sharedpreference
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sharedpreference.databinding.ActivityMainBinding
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var bind: ActivityMainBinding
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

        MainScope().launch{
            val pref: SharedPreferences=getSharedPreferences("login",MODE_PRIVATE)
            val check: Boolean=pref.getBoolean("flag",false)
            val user=pref.getString("user","World!!")
            bind.text.text="Hello $user"
            delay(2000)

            if(check) {
                startActivity(Intent(this@MainActivity, HomeActivity::class.java))
            } else
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))

        }

    }
}