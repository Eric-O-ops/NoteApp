package com.geektech.noteapp.ui.fragments.noteapp.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.geektech.noteapp.App
import com.geektech.noteapp.R
import com.geektech.noteapp.databinding.FragmentNoteAppDetailBinding
import com.geektech.noteapp.models.NoteModel
import java.text.SimpleDateFormat
import java.util.*

class NoteAppDetailFragment : Fragment() {

    private lateinit var binding: FragmentNoteAppDetailBinding

    private var backgroundNoteColor: String = COLOR_BACKGROUND_ONE

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
        selectColor()

    }

    private fun sendNewNote() {
        binding.textConfirmNote.setOnClickListener {
                val title = binding.title.text.toString()
                val description = binding.directions.text.toString()
                val date = getData()
                val time = getTime()

            if (time.isNotEmpty() || description.isNotEmpty()) {

                App.getDataIntense()?.getNoteDao()?.insert(
                    NoteModel(title, description, date, time, backgroundNoteColor)
                )

            }else{
                Toast.makeText(requireContext(), "Massage mustn't empty", Toast.LENGTH_SHORT).show()

            }

            findNavController().navigate(
                R.id.action_noteAppDetailFragment_to_noteAppMainFragment
            )
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
    private fun selectColor() = with(binding){
        colorOne.setOnClickListener {
            radioBottomOne.isChecked = true
            backgroundNoteColor  = COLOR_BACKGROUND_ONE

        }

        colorTwo.setOnClickListener {
            radioBottomTwo.isChecked = true
            backgroundNoteColor  = COLOR_BACKGROUND_TWO

        }

        colorThree.setOnClickListener {
            radioBottomThree.isChecked = true
            backgroundNoteColor  = COLOR_BACKGROUND_THREE

        }
    }

    // TODO: change note 
    private fun changeNote(){
        if (App.preferenceHelper.isChangeNote()){
            binding.time.visibility = View.GONE
            binding.data.visibility = View.GONE

        }
        binding.textConfirmNote.setOnClickListener {




        }



    }

    private companion object{

        const val COLOR_BACKGROUND_ONE = "#191818"
        const val COLOR_BACKGROUND_TWO = "#EBE4C9"
        const val COLOR_BACKGROUND_THREE = "#571818"

    }
}