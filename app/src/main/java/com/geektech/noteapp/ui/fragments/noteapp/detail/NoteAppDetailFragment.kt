package com.geektech.noteapp.ui.fragments.noteapp.detail

import android.annotation.SuppressLint
import android.app.StatusBarManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.geektech.noteapp.App
import com.geektech.noteapp.R
import com.geektech.noteapp.databinding.FragmentNoteAppDetailBinding
import com.geektech.noteapp.models.NoteModel
import com.geektech.noteapp.setBackStackData
import java.text.SimpleDateFormat
import java.util.*

class NoteAppDetailFragment : Fragment() {

    private lateinit var binding: FragmentNoteAppDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteAppDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sendNewNote()
        setUpDataAndTime()
        goToNoteMainFragment()

    }

    private fun sendNewNote() {
        binding.textConfirmNote.setOnClickListener {
                val title = binding.title.text.toString()
                val description = binding.directions.text.toString()
                val date = getData()
                val time = getTime()

            App.getDataIntense()?.getNoteDao()?.insert(
                NoteModel(title,description,date,time)
            )
            findNavController().navigate(
                R.id.action_noteAppDetailFragment_to_noteAppMainFragment
            )
                val newNote:NoteModel = NoteModel(title,description,"12.03.22","12:00")
                setBackStackData("newNote",newNote,true)
        }
    }

    private fun goToNoteMainFragment(){
        binding.back.setOnClickListener{
            findNavController().navigate(
                R.id.action_noteAppDetailFragment_to_noteAppMainFragment
            )
        }
    }

    private fun setUpDataAndTime(){
        binding.data.text = getData()
        binding.time.text = getTime()

    }

    @SuppressLint("SimpleDateFormat")
    private fun getData(): String{
        val dataFormat = SimpleDateFormat("d MMMM")
        val data = dataFormat.format(Calendar.getInstance().time)

        return data
    }

    @SuppressLint("SimpleDateFormat")
    private fun getTime(): String{
        val timeFormat = SimpleDateFormat("HH:mm")
        val time = timeFormat.format(Calendar.getInstance().time)

        return time
    }
}