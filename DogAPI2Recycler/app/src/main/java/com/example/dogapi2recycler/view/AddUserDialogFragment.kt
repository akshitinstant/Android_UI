package com.example.dogapi2recycler.view

import android.app.Dialog
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.dogapi2recycler.R
import com.example.dogapi2recycler.databinding.FragmentAddUserDialogBinding
import com.example.dogapi2recycler.databinding.FragmentDogProfileBinding
import com.example.dogapi2recycler.model.User
import com.example.dogapi2recycler.view.LaunchActivity
import com.example.dogapi2recycler.viewmodel.MyViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.getValue
import kotlin.toString

class AddUserDialogFragment : DialogFragment() {
    lateinit var bind: FragmentAddUserDialogBinding
    val myVM: MyViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        bind = FragmentAddUserDialogBinding.inflate(inflater)
        return bind.root
    }

    override fun onStart() {
        super.onStart()
        // Access the dialog's window
        val window = dialog?.window
        if (window != null) {
            // Set custom background to remove default padding
            window.setBackgroundDrawableResource(R.drawable.bg_add_user_dialog_theme)

            // Set custom size (force width and height)
            window.setLayout(
                convertDpToPx(300),
                convertDpToPx(320)
            )
        }
    }

    fun convertDpToPx(dp: Int): Int {
        val density = resources.displayMetrics.density
        return (dp * density).toInt()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind.addUser.setOnClickListener {
            val activity = requireActivity()

            val name = bind.nameEditText.text.toString().uppercase().trim()
            val email = bind.emailEditText.text.toString().lowercase().trim()
            val password = bind.passwordEditText.text.toString()
            try {
                if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Log.d(
                        "emailForm8",
                        "Check: $email --- ${email.isEmpty()} --- ${
                            Patterns.EMAIL_ADDRESS.matcher(email).matches()
                        }"
                    )
                    throw IllegalArgumentException("Invalid email format")
                }

                if (name.isEmpty()) {
                    throw IllegalArgumentException("Name cannot be empty")
                }

                if (password.length < 8) {
                    throw IllegalArgumentException("Password must be longer than or equal to 8 characters")
                }

                CoroutineScope(Dispatchers.IO).launch {
                    if (myVM.getDB(activity).userDAO().checkEmail(email) != null) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(activity, "Email already registered", Toast.LENGTH_SHORT)
                                .show()
                        }
                        return@launch
                    }

                    val pref = activity.getSharedPreferences("appSharedPref", MODE_PRIVATE)
                    val emailAddress = pref.getString("email", "?").toString()
                    lifecycleScope.launch(Dispatchers.IO) {
                        myVM.getDB(activity).userDAO().updateActiveStatus(emailAddress, false)
                    }
                    pref.edit().clear().apply()


                    myVM.getDB(activity).userDAO()
                        .insertUser(User(name = name, email = email, password = password))
                    val user = myVM.getDB(activity).userDAO().verify(email, password)
                    withContext(Dispatchers.Main) {
                        if (user != null) {
                            val active = true
                            lifecycleScope.launch(Dispatchers.IO) {
                                myVM.getDB(activity).userDAO().updateActiveStatus(user.email, active)
                            }
                            val pref = activity.getSharedPreferences("appSharedPref", MODE_PRIVATE)
                            val editor = pref.edit()
                            editor.apply {
                                putBoolean("loggedIn", true)
                                putInt("id", user.id)
                                putString("name", user.name)
                                putString("email", user.email)
                                putBoolean("active", user.active)
                            }.apply()
                            startActivity(Intent(activity, MainActivity::class.java))
                            activity.finish()
                        } else {
                            Toast.makeText(activity, "Something went wrong!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(activity, "${e.message}, Try Again!", Toast.LENGTH_SHORT).show()
            }
        }
    }

}