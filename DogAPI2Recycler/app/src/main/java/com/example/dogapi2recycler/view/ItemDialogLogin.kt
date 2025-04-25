package com.example.dogapi2recycler.view

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.dogapi2recycler.R
import com.example.dogapi2recycler.databinding.FragmentItemDialogLoginBinding
import com.example.dogapi2recycler.databinding.FragmentLogoutDialogBinding
import com.example.dogapi2recycler.view.UserListActivity
import com.example.dogapi2recycler.viewmodel.MyViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.getValue

class ItemDialogLogin : DialogFragment() {
    lateinit var bind: FragmentItemDialogLoginBinding
    val myVM: MyViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {

        bind = FragmentItemDialogLoginBinding.inflate(inflater)
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
                convertDpToPx(300),  // Custom width in dp
                convertDpToPx(260)   // Custom height in dp
            )
        }
    }

    fun convertDpToPx(dp: Int): Int {
        val density = resources.displayMetrics.density
        return (dp * density).toInt()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val activity = requireActivity()
        val pref = activity.getSharedPreferences("appSharedPref", MODE_PRIVATE)
        val emailAddress = pref.getString("email", "?").toString()
        bind.emailText.text = arguments?.getString("email")?:"?"
        bind.loginUser.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                myVM.getDB(activity).userDAO().updateActiveStatus(emailAddress, false)
            }
            val editor = pref.edit()
            editor.clear().apply()
            val email = bind.emailText.text.toString().lowercase().trim()
            val password = bind.passwordEditText.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
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
                        activity.finishAffinity()
                    } else {
                        Toast.makeText(activity, "Something went wrong!, Try Again!", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }

    }
}