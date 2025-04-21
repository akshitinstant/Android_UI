package com.example.ui_assessment.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ui_assessment.R
import com.example.ui_assessment.databinding.FragmentCategoryBinding
import com.example.ui_assessment.viewmodels.MyViewModel

class CategoryFrag : Fragment() {
    lateinit var f1Bind: FragmentCategoryBinding
    lateinit var myViewModel: MyViewModel
    lateinit var adapter: VerticalAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
//        f1Bind = DataBindingUtil.inflate(inflater,R.layout.fragment_category, container, false)
        f1Bind = FragmentCategoryBinding.inflate(inflater, container, false)
        myViewModel = ViewModelProvider(this)[MyViewModel::class.java]
        return f1Bind.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        f1Bind.frag1ForwardArrow.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.container, ListFrag())
                .addToBackStack(null).commit()
        }

        observeList()
        //initialiseAdapter()


    }

    private fun initialiseAdapter() {
        val recyclerView: RecyclerView = f1Bind.frag1VerticalRecycler
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//        adapter = VerticalAdapter(arrayListOf())
        adapter = VerticalAdapter(myViewModel.list)
        recyclerView.adapter = adapter    }

    private fun observeList() {
        myViewModel.getFoodList().observe(viewLifecycleOwner){
            if(it!=null) {
                myViewModel.list = it
                initialiseAdapter()
            }
//            list->list?.let{
//                myViewModel.list=it
//            adapter.updateList(it)
//            }
        }
    }

}