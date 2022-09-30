package com.geektech.noteapp.ui.fragments.noteapp

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.geektech.noteapp.App
import com.geektech.noteapp.R
import com.geektech.noteapp.databinding.FragmentNoteAppMainBinding
import com.geektech.noteapp.models.NoteModel
import com.geektech.noteapp.ui.adapters.NoteAdapter
import com.geektech.noteapp.ui.adapters.OnNoteClickListener

class NoteAppMainFragment : Fragment(), OnNoteClickListener {

    private lateinit var binding: FragmentNoteAppMainBinding

    private var noteList:List<NoteModel> = ArrayList()

    private val adapter:NoteAdapter = NoteAdapter(noteList,this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteAppMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeRecycleViewAdapter()
        getNoteAndAddToList()
        addNote()
    }

    private fun initializeRecycleViewAdapter() {
        binding.recycleView.layoutManager = LinearLayoutManager(requireContext())
        binding.recycleView.adapter = adapter
    }

    private fun addNote(){
        binding.addNewNote.setOnClickListener {

            findNavController().navigate(
               R.id.action_noteAppMainFragment_to_noteAppDetailFragment
            )
        }
    }

    private fun getNoteAndAddToList(){
        App.getDataIntense()?.getNoteDao()?.getAllModels()?.observe(viewLifecycleOwner){
            adapter.setList(it)
        }

//        getBackStackData<NoteModel>(
//            "newNote"
//        , {
//               noteList.add(it)
//
//        })
    }

    override fun onClick(model: NoteModel) {
        val alertDialog = AlertDialog.Builder(requireContext())
            .setCancelable(false)
            .setTitle("Удалить заметку")
            .setMessage("Вы действительно хотите удалить заметку?")
            .setPositiveButton("да", DialogInterface.OnClickListener {dialogInterface, i ->dialogInterface
                App.getDataIntense()?.getNoteDao()?.delete(model)

            })
            .setNegativeButton("нет",DialogInterface.OnClickListener{dialogInterface, i ->dialogInterface

            }).create().show()
    }
}