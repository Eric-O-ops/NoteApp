package com.geektech.noteapp.ui.fragments.noteapp.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geektech.noteapp.databinding.FragmentNoteAppDetailBinding
import com.geektech.noteapp.setBackStackData

class NoteAppDetailFragment : Fragment() {

    private lateinit var binding: FragmentNoteAppDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteAppDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sendNewNote()
    }

    private fun sendNewNote() {
        binding.textConfirmNote.setOnClickListener {

                val title = binding.textNote.text.toString()

                setBackStackData("key",title,true)
        }
    }
}