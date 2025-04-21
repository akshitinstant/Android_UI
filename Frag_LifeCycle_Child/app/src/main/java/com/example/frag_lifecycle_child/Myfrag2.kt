package com.example.frag_lifecycle_child

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class Myfrag2 : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("frag" , "MyFragment2 -> onCreateView()")
       var view:View = inflater.inflate(R.layout.fragment_myfrag2, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { super.onViewCreated(view, savedInstanceState)
        Log.d("frag" , "MyFragment2 -> onViewCreated()")

        val frag3= MyFrag3()
        val bundle = Bundle()
        bundle.putString("f2f", "Frag2 to Frag3")
        frag3.arguments=bundle

        view.findViewById<Button>(R.id.f2Tof3).setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.conatiner, frag3).commit()
        }
    }
    override fun onDestroyView() { super.onDestroyView(); Log.d("frag","MyFragment2 -> onDestroyView()") }
    override fun onDestroy() { super.onDestroy(); Log.d("frag","MyFragment2 -> onDestroy()") }
    override fun onStart() { super.onStart(); Log.d("frag","MyFragment2 -> onStart()") }
    override fun onPause() { super.onPause(); Log.d("frag","MyFragment2 -> onPause()") }
    override fun onStop() { super.onStop(); Log.d("frag","MyFragment2 -> onStop()") }
    override fun onResume() { super.onResume(); Log.d("frag","MyFragment2 -> onResume()") }
    override fun onAttach(context: Context) { super.onAttach(context); Log.d("frag","MyFragment2 -> onAttach()") }
    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState); Log.d("frag", "MyFragment2 -> onCreate()") }
    override fun onDetach() { super.onDetach(); Log.d("frag" , "MyFragment2 -> onDetach()") }


}