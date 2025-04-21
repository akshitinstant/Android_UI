package com.example.frag_lifecycle_child

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class MyFrag3 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d("frag" , "MyFragment3 -> onCreateView()")
        var view:View =inflater.inflate(R.layout.fragment_my_frag3, container, false)

        view.findViewById<Button>(R.id.navBtnToConatiner).setOnClickListener {
            parentFragmentManager.beginTransaction().add(R.id.conatiner, Myfrag2()).commit()
        }
        view.findViewById<Button>(R.id.navBtnToChildContainer).setOnClickListener {
           var cfm=childFragmentManager
//            cfm.popBackStack()
            cfm.beginTransaction().add(R.id.childContainer, MyFrag1()).commit()

        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);

        val data=arguments?.getString("f2f")?:"Empty"
        val textView=view.findViewById<TextView>(R.id.txtFrag3)
        textView.text="${textView.text} \n $data"

        Log.d("frag" , "MyFragment3 -> onViewCreated()")
        childFragmentManager.beginTransaction().add(R.id.childContainer, MyFragChild()).commit();
    }

    override fun onDestroyView() { super.onDestroyView(); Log.d("frag","MyFragment3 -> onDestroyView()") }
    override fun onDestroy() { super.onDestroy(); Log.d("frag","MyFragment3 -> onDestroy()") }
    override fun onStart() { super.onStart(); Log.d("frag","MyFragment3 -> onStart()") }
    override fun onPause() { super.onPause(); Log.d("frag","MyFragment3 -> onPause()") }
    override fun onStop() { super.onStop(); Log.d("frag","MyFragment3 -> onStop()") }
    override fun onResume() { super.onResume(); Log.d("frag","MyFragment3 -> onResume()") }
    override fun onAttach(context: Context) { super.onAttach(context); Log.d("frag","MyFragment3 -> onAttach()") }
    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState); Log.d("frag", "MyFragment3 -> onCreate()") }
    override fun onDetach() { super.onDetach(); Log.d("frag" , "MyFragment3 -> onDetach()") }


}

