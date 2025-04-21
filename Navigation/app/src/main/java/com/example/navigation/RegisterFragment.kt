package com.example.navigation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.navigation.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {
    lateinit var bind: FragmentRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        bind = FragmentRegisterBinding.inflate(inflater)

        bind.registerBtn.setOnClickListener {
            val pref = requireContext().getSharedPreferences("auth", Context.MODE_PRIVATE)
            val check = pref.getBoolean("loggedIn", false)
            if (check)
                findNavController().navigate(R.id.action_registerFragment_to_mainFragment)
            else
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        return bind.root
    }

}