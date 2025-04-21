package com.example.frag_lifecycle_child

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class MyFragChild : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("frag" , "ChildFragment -> onCreateView()")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_frag_child, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { super.onViewCreated(view, savedInstanceState); Log.d("frag" , "ChildFragment -> onViewCreated()") }
    override fun onDestroyView() { super.onDestroyView(); Log.d("frag","ChildFragment -> onDestroyView()") }
    override fun onDestroy() { super.onDestroy(); Log.d("frag","ChildFragment -> onDestroy()") }
    override fun onStart() { super.onStart(); Log.d("frag","ChildFragment -> onStart()") }
    override fun onPause() { super.onPause(); Log.d("frag","ChildFragment -> onPause()") }
    override fun onStop() { super.onStop(); Log.d("frag","ChildFragment -> onStop()") }
    override fun onResume() { super.onResume(); Log.d("frag","ChildFragment -> onResume()") }
    override fun onAttach(context: Context) { super.onAttach(context); Log.d("frag","ChildFragment -> onAttach()") }
    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState); Log.d("frag", "ChildFragment -> onCreate()") }
    override fun onDetach() { super.onDetach(); Log.d("frag" , "ChildFragment -> onDetach()") }


}