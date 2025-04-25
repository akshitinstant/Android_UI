package com.example.dogapi2recycler.view

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.service.voice.VisibleActivityInfo
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.dogapi2recycler.R
import com.example.dogapi2recycler.databinding.ActivityLaunchBinding
import com.example.dogapi2recycler.db.AppDB
import com.example.dogapi2recycler.model.User
import com.example.dogapi2recycler.viewmodel.MyViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LaunchActivity : AppCompatActivity() {
    lateinit var bind: ActivityLaunchBinding
    val myVM: MyViewModel by viewModels()
    var user: User? = null
    lateinit var db: AppDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        bind = ActivityLaunchBinding.inflate(layoutInflater)
        setContentView(bind.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        db = myVM.getDB(this)

        val pref = getSharedPreferences("appSharedPref", MODE_PRIVATE)
        val check: Boolean = pref.getBoolean("loggedIn", false)
        if (check) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        val emailAddress = intent.getStringExtra("emailAddress") ?: ""
        bind.emailEditText.setText(emailAddress)

    }

    fun toSignUpPage(view: View) {
        bind.loginLayout.visibility = View.GONE
        bind.signupLayout.visibility = View.VISIBLE
    }

    fun toLogInPage(view: View) {
        bind.loginLayout.visibility = View.VISIBLE
        bind.signupLayout.visibility = View.GONE
    }

    fun registerUser(view: View) {
        val name = bind.nameEditText.text.toString().uppercase().trim()
        val email = bind.emailEditTextS.text.toString().lowercase().trim()
        val password = bind.passwordEditTextS.text.toString()
        try {
//            val emailRegex = Regex("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$")
            if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Log.d("emailForm8", "Check: $email --- ${email.isEmpty()} --- ${Patterns.EMAIL_ADDRESS.matcher(email).matches()}")
                throw IllegalArgumentException("Invalid email format")
            }

            if (name.isEmpty()) {
                throw IllegalArgumentException("Name cannot be empty")
            }

            if (password.length < 8) {
                throw IllegalArgumentException("Password must be longer than or equal to 8 characters")
            }

            CoroutineScope(Dispatchers.IO).launch {
                if (myVM.getDB(this@LaunchActivity).userDAO().checkEmail(email) != null) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@LaunchActivity,
                            "Email already registered",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                    return@launch
                }
                db.userDAO().insertUser(User(name = name, email = email, password = password))

                withContext(Dispatchers.Main) {
                    bind.loginLayout.visibility = View.VISIBLE
                    bind.signupLayout.visibility = View.GONE
                    bind.emailEditText.setText(email)
                }
            }
        } catch (e: Exception) {
            Toast.makeText(this, "${e.message}, Try Again!", Toast.LENGTH_SHORT).show()
        }
    }

    fun logInUser(view: View) {
        val email = bind.emailEditText.text.toString().lowercase().trim()
        val password = bind.passwordEditText.text.toString()

        CoroutineScope(Dispatchers.IO).launch {
            val user = db.userDAO().verify(email, password)

            withContext(Dispatchers.Main) {
                if (user != null) {
                    val active = true
                    lifecycleScope.launch(Dispatchers.IO) {
                        db.userDAO().updateActiveStatus(user.email, active)
                    }
                    val pref = getSharedPreferences("appSharedPref", MODE_PRIVATE)
                    val editor = pref.edit()
                    editor.apply {
                        putBoolean("loggedIn", true)
                        putInt("id", user.id)
                        putString("name", user.name)
                        putString("email", user.email)
                        putBoolean("active", user.active)
                    }.apply()

                    startActivity(Intent(this@LaunchActivity, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this@LaunchActivity, "Invalid Credentials, Try Again!", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}