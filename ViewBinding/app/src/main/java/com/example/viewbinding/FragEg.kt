package com.example.viewbinding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.viewbinding.databinding.FragEgBinding

class FragEg : Fragment() {
  private lateinit var fragBind : FragEgBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragBind= FragEgBinding.inflate(inflater, container, false)
    return fragBind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragBind.fragBtn.setOnClickListener { fragBind.fragTxt.text= getString(R.string.clicked) }
    }
}