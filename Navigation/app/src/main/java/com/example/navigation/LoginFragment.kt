package com.example.navigation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.navigation.databinding.FragmentLoginBinding
import androidx.core.content.edit


class LoginFragment : Fragment() {
lateinit var bind: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        bind= FragmentLoginBinding.inflate(inflater)
        bind.loginBtn.setOnClickListener {
            var pref=requireContext().getSharedPreferences("auth",Context.MODE_PRIVATE)
            pref.edit {
                putBoolean("loggedIn", true)
                putString("user", "Akshay!")
            }
            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        }
        return bind.root
    }

}