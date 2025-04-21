package com.example.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.navigation.databinding.FragmentDialogBinding


class MyDialogFragment : DialogFragment() {
    lateinit var bind: FragmentDialogBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind= FragmentDialogBinding.inflate(inflater)
        bind.yesButton.setOnClickListener {
            findNavController().navigate(R.id.activity2)    // direct without creating action
        }
        return bind.root
    }


}