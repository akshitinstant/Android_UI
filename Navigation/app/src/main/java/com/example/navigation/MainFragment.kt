package com.example.navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.system.Os.remove
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.navigation.databinding.FragmentMainBinding
import androidx.core.content.edit
import androidx.navigation.NavOptions


class MainFragment : Fragment() {
lateinit var bind: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        bind= FragmentMainBinding.inflate(inflater)
        val pref=requireContext().getSharedPreferences("auth",Context.MODE_PRIVATE)
        val uri = requireActivity().intent?.data
        if (requireActivity().intent?.action == Intent.ACTION_VIEW && uri != null) {
//            val id = uri.getQueryParameter("id")
            val name = uri.getQueryParameter("uname")
            bind.username.text = "Hey $name!"
        } else {
            val user=pref.getString("user",null)
            bind.username.text="Hey $user!"
        }

        bind.notesBtn.setOnClickListener {
            val note="This is my first note."
            val action= MainFragmentDirections.actionMainFragmentToNoteFragment(note)
            findNavController().navigate(action)
        }
        bind.logout.setOnClickListener {
            pref.edit() {
                clear()
            }
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.registerFragment, true)    // alternate to finishAffinity()- clear the entire back stack
                .build()
            findNavController().navigate(R.id.action_mainFragment_to_registerFragment,null,navOptions)
        }
    return bind.root
    }

}