package com.example.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view : View = inflater.inflate(R.layout.fragment_my, container, false)
        var str=view.findViewById<TextView>(R.id.fragtxt)
        Log.d("frag","MyFragment -> onCreateView()")
        return view
    }

    override fun onDestroyView() { super.onDestroyView(); Log.d("frag","MyFragment -> onDestroyView()") }
    override fun onDestroy() { super.onDestroy(); Log.d("frag","MyFragment -> onDestroy()") }
    override fun onStart() { super.onStart(); Log.d("frag","MyFragment -> onStart()") }
    override fun onPause() { super.onPause(); Log.d("frag","MyFragment -> onPause()") }
    override fun onStop() { super.onStop(); Log.d("frag","MyFragment -> onStop()") }
    override fun onResume() { super.onResume(); Log.d("frag","MyFragment -> onResume()") }
    override fun onAttach(context: Context) { super.onAttach(context); Log.d("frag","MyFragment -> onAttach()") }
    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState); Log.d("frag", "MyFragment -> onCreate()") }
    override fun onDetach() { super.onDetach(); Log.d("frag" , "MyFragment -> onDetach()") }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { super.onViewCreated(view, savedInstanceState); super.onDetach(); Log.d("frag" , "MyFragment -> onViewCreated()")}

}

