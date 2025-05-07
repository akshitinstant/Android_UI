package com.example.contentproviderexample

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contentproviderexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ContactAdapter
    private val list: MutableLiveData<MutableSet<String>> = MutableLiveData()

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                fetchContacts()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Setup RecyclerView
        adapter = ContactAdapter(emptyList())
        binding.recyclerViewContacts.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewContacts.adapter = adapter

        // Observe LiveData only once in onCreate
        list.observe(this, Observer { contacts ->
            contacts?.let {
                adapter.updateList(it.toList())
            }
        })

        // Check permission and fetch contacts
        sdkIntAboveOreo {
            isPermissionGranted(this, android.Manifest.permission.READ_CONTACTS) { granted ->
                if (granted) {
                    fetchContacts()
                } else {
                    requestPermissionLauncher.launch(android.Manifest.permission.READ_CONTACTS)
                }
            }
        }
    }


    private fun getContactList(): MutableSet<String> {
        val set = mutableSetOf<String>()
        val cursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null, null, null, null
        )
        cursor?.use {
            val nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
            while (cursor.moveToNext()) {
                val name = cursor.getString(nameIndex)
                set.add(name)
            }
        }
        return set
    }

    private fun fetchContacts() {
        list.postValue(getContactList())
    }

    private inline fun sdkIntAboveOreo(call: () -> Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            call()
        }
    }

    private inline fun isPermissionGranted(
        context: Context,
        permission: String,
        call: (Boolean) -> Unit
    ) {
        call(ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED)
    }

    fun nextAct(view: View) {
        startActivity(Intent(this, MainActivity2::class.java))
    }
}
