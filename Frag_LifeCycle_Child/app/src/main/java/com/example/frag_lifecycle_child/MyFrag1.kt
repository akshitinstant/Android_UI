package com.example.frag_lifecycle_child

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class MyFrag1 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("frag","MyFragment1 -> onCreateView()")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_frag1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { super.onViewCreated(view, savedInstanceState);Log.d("frag" , "MyFragment1 -> onViewCreated()") }
    override fun onDestroyView() { super.onDestroyView(); Log.d("frag","MyFragment1 -> onDestroyView()") }
    override fun onDestroy() { super.onDestroy(); Log.d("frag","MyFragment1 -> onDestroy()") }
    override fun onStart() { super.onStart(); Log.d("frag","MyFragment1 -> onStart()") }
    override fun onPause() { super.onPause(); Log.d("frag","MyFragment1 -> onPause()") }
    override fun onStop() { super.onStop(); Log.d("frag","MyFragment1 -> onStop()") }
    override fun onResume() { super.onResume(); Log.d("frag","MyFragment1 -> onResume()") }
    override fun onAttach(context: Context) { super.onAttach(context); Log.d("frag","MyFragment1 -> onAttach()") }
    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState); Log.d("frag", "MyFragment1 -> onCreate()") }
    override fun onDetach() { super.onDetach(); Log.d("frag" , "MyFragment1 -> onDetach()") }

}