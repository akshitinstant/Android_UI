package com.example.dogapi2recycler.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dogapi2recycler.R
import com.example.dogapi2recycler.adapters.ListAdapter
import com.example.dogapi2recycler.databinding.ActivityListBinding
import com.example.dogapi2recycler.model.DogClickListener
import com.example.dogapi2recycler.model.Dog
import com.example.dogapi2recycler.viewmodel.MyViewModel

class ListActivity : AppCompatActivity(), DogClickListener {

    lateinit var bind: ActivityListBinding
    lateinit var adapter: ListAdapter
    lateinit var searchView: SearchView

    val myVM: MyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        bind = ActivityListBinding.inflate(layoutInflater)
        setContentView(bind.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        myVM.getDogList().observe(this) {
            if (it != null) {
                myVM.list = it
                initializeAdapter()
            }
        }

        searchView = bind.searchBar

        val searchEditText = searchView.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
        searchEditText.setTextColor(ContextCompat.getColor(this, R.color.black))
        searchEditText.setHintTextColor(ContextCompat.getColor(this, R.color.black))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newStr: String): Boolean {
                val newText= newStr.trim()
                val filteredList = ArrayList<Dog>()
                if(newText.length>3)
                myVM.list.forEach {
                    if (it.name.lowercase().contains(newText.lowercase())) {
                        filteredList.add(it)
                    }
                } else myVM.list.forEach {
                        filteredList.add(it)
                }


                if (filteredList.isEmpty()) {
//                    Toast.makeText(this@ListActivity, "No Data Found", Toast.LENGTH_SHORT).show()
                    bind.listRecycler.visibility=View.GONE
                    bind.noResultText.visibility=View.VISIBLE
                } else {
                    bind.listRecycler.visibility=View.VISIBLE
                    bind.noResultText.visibility=View.GONE
                    adapter.setFilteredList(filteredList)
                }
                return true
            }
        })

    }


    fun initializeAdapter() {
        bind.progressBar.visibility = View.GONE

        val recyclerView = bind.listRecycler
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ListAdapter(this, myVM.list)
        recyclerView.adapter = adapter
    }

    override fun customClickListener(dog: Dog) {

        val dogP = DogProfile()
        val bundle = Bundle().apply {
            putString("name", dog.name)
            putString("breed", dog.breed_group)
            putString("life", dog.life_span)
            putString("for", dog.bred_for)
            putString("image", dog.reference_image_id)
            putString("imgURL", myVM.getImageURL())
            putString("height", dog.height.metric)
            putString("weight", dog.weight.metric)
            putString("temperament", dog.temperament)
            putString("origin", dog.origin)
        }
        dogP.arguments = bundle
        bind.activityContent.visibility = View.GONE
        bind.container.visibility = View.VISIBLE
        supportFragmentManager.beginTransaction().replace(R.id.container, dogP).addToBackStack(null)
            .commit()
    }

    fun back(view: View){
        startActivity(Intent(this, MainActivity::class.java))
    }

}