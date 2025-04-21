package com.example.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.navigation.databinding.FragmentNoteBinding

class NoteFragment : Fragment() {
    lateinit var binding: FragmentNoteBinding
    val args: NoteFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentNoteBinding.inflate(inflater)
        binding.openDialog.setOnClickListener {
            findNavController().navigate(R.id.action_noteFragment_to_dialogFragment)
        }
        val note=args.note
        binding.notes.text=note
        return binding.root
    }
}