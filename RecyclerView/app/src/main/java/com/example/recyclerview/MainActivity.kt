package com.example.recyclerview

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.buildSpannedString
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), CustomClickListener {
    lateinit var contactAdapter : RecycleContactAdapter
    val arrContacts = ArrayList<ContactModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val recycleView = findViewById<RecyclerView>(R.id.recycleContact)
//        recycleView.setLayoutManager(LinearLayoutManager(this))
        recycleView.layoutManager = LinearLayoutManager(this)
//        recycleView.layoutManager= GridLayoutManager(this, 2)

        arrContacts.add(ContactModel(R.drawable.m_blueandroidos, "Blue Android", "8756789878"))
        arrContacts.add(ContactModel(R.drawable.s_playstore, "Play Store", "8756754345"))
        arrContacts.add(ContactModel(R.drawable.s_messagebot, "Message Bot", "8756789878"))
        arrContacts.add(ContactModel(R.drawable.chat, "Chat", "8756789878"))
        arrContacts.add(ContactModel(R.drawable.home, "Home", "8756789878"))
        arrContacts.add(ContactModel(R.drawable.doctor, "Doctor", "8756789878"))
        arrContacts.add(
            ContactModel(
                R.drawable.l_bluewireandroidos,
                "Blue Wire Android",
                "8756789878"
            )
        )
        arrContacts.add(ContactModel(R.drawable.l_workspace, "Work Space", "8756789878"))
        arrContacts.add(ContactModel(R.drawable.l_yellowandroidos, "Yellow Android", "8756789878"))
        arrContacts.add(ContactModel(R.drawable.m_androidstudio, "Android Studio", "8756789878"))
        arrContacts.add(ContactModel(R.drawable.banner, "Banner", "8756789878"))
        arrContacts.add(ContactModel(R.drawable.patient, "Patient", "8756789878"))


        contactAdapter = RecycleContactAdapter(arrContacts, this)
        recycleView.adapter = contactAdapter

        findViewById<FloatingActionButton>(R.id.addBtn).setOnClickListener {
            contactAdapter.addItem(ContactModel(R.drawable.patient, "New Patient", "8756789878"))
//            contactAdapter.addList(newContacts)
        }

    }

    val newContacts: ArrayList<ContactModel> = arrayListOf(
        ContactModel(R.drawable.ic_launcher_foreground,"newConatct 1","12334"),
        ContactModel(R.drawable.ic_launcher_foreground,"newConatct 2","12335"),
        ContactModel(R.drawable.ic_launcher_foreground,"newConatct 3","12336"))

    override fun cutomClickListener(view: View, position: Int) {
//        Toast.makeText(this, "Item ${position + 1} Clicked", Toast.LENGTH_SHORT).show()
//        contactAdapter.updateItem(position)
        contactAdapter.deleteItem(position)       //TODO: Crash
    }
}