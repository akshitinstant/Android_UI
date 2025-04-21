package com.example.ui_assessment.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ui_assessment.R
import com.example.ui_assessment.databinding.Frag2RecyclerLayoutBinding
import com.example.ui_assessment.databinding.FragmentListBinding
import com.example.ui_assessment.models.FoodItem
import com.example.ui_assessment.viewmodels.MyViewModel

class ListFrag : Fragment() {
    private lateinit var binding: FragmentListBinding
    private lateinit var searchView: SearchView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.frag2Recycler.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL, false
        )
        val itemList = MyViewModel().getFoodItems()
        val adapter = Frag2RecyclerAdapter(itemList)
        binding.frag2Recycler.adapter = adapter

        binding.frag2BackArrow.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        searchView = binding.frag2SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredList = ArrayList<FoodItem>()

                itemList.forEach { item ->
                    if (item.name.lowercase().contains(newText.toString().lowercase())) {
                        filteredList.add(item)
                    }
                }


                if (filteredList.isEmpty()) {
                    Toast.makeText(requireContext(), "No Data Found", Toast.LENGTH_SHORT).show()
                } else {
                    adapter.setFilteredList(filteredList)
                }
                return true
            }


        })

    }

}
