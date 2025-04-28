package com.example.dogapi2recycler.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dogapi2recycler.R
import com.example.dogapi2recycler.adapters.ListAdapter
import com.example.dogapi2recycler.adapters.UserAdapter
import com.example.dogapi2recycler.databinding.ActivityUserListBinding
import com.example.dogapi2recycler.databinding.FragmentAddUserDialogBinding
import com.example.dogapi2recycler.model.User
import com.example.dogapi2recycler.model.UserClickListener
import com.example.dogapi2recycler.viewmodel.MyViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class UserListActivity : AppCompatActivity(), UserClickListener {
    val myVM: MyViewModel by viewModels()
    lateinit var adapter: UserAdapter
    lateinit var bind: ActivityUserListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        bind = ActivityUserListBinding.inflate(layoutInflater)
        setContentView(bind.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val db = myVM.getDB(this)
        db.userDAO().getAllRecord().observe(this) {
            if (it != null) {
                myVM.userList = it
            }
            initializeAdapter()
        }
    }

    fun initializeAdapter() {
        val recyclerView = bind.userRecycler
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = UserAdapter(this, myVM.userList)
        recyclerView.adapter = adapter
    }

    fun back(view: View) {
        startActivity(Intent(this, MainActivity::class.java))
        finishAffinity()
    }

    override fun customClickListener(user: User) {
//        val pref = getSharedPreferences("appSharedPref", MODE_PRIVATE)
//        val editor=pref.edit()
//        val email=user.email.toString()
//        editor.putString("email",email)
//        editor.apply()
        val myDialog = ItemDialogLogin()
        val bundle = Bundle()
        bundle.putString("email", user.email)
        myDialog.arguments = bundle
        myDialog.show(supportFragmentManager, "LoginUserDialog")
    }

    fun addUser(view: View) {
        val myDialog = AddUserDialogFragment()
        myDialog.show(supportFragmentManager, "AddUserDialog")
    }

}
