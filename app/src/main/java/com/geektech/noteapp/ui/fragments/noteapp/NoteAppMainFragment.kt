package com.geektech.noteapp.ui.fragments.noteapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.geektech.noteapp.R
import com.geektech.noteapp.databinding.FragmentNoteAppMainBinding
import com.geektech.noteapp.getBackStackData
import com.geektech.noteapp.models.NoteModel
import com.geektech.noteapp.ui.adapters.NoteAdapter

class NoteAppMainFragment : Fragment() {

    private lateinit var binding: FragmentNoteAppMainBinding

    private val noteList:ArrayList<NoteModel> = ArrayList()

    private val adapter = NoteAdapter(noteList)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteAppMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeRecycleViewAdapter()
        addNote()
        getNoteAndAddToList()
    }

    private fun initializeRecycleViewAdapter() {
        binding.recycleView.layoutManager = LinearLayoutManager(requireContext())
        binding.recycleView.adapter = adapter
    }

    private fun addNote(){
        binding.buttonAddNote.setOnClickListener {

            findNavController().navigate(
               R.id.action_noteAppMainFragment_to_noteAppDetailFragment
            )
        }
    }

    private fun getNoteAndAddToList(){
        getBackStackData<String>(
            "key"
        , {
            noteList.add(NoteModel(it))

        })
    }
}