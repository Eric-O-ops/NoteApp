package com.geektech.noteapp.ui.fragments.noteapp

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.geektech.noteapp.App
import com.geektech.noteapp.R
import com.geektech.noteapp.databinding.FragmentNoteAppMainBinding
import com.geektech.noteapp.models.NoteModel
import com.geektech.noteapp.ui.adapters.NoteAdapter
import com.geektech.noteapp.ui.adapters.OnNoteClickListener

class NoteAppMainFragment : Fragment(), OnNoteClickListener {

    private lateinit var binding: FragmentNoteAppMainBinding

    private val adapter:NoteAdapter = NoteAdapter(this,this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        App.preferenceHelper.putValue("isLinear", true)
        binding = FragmentNoteAppMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeRecycleViewAdapter()
        getNoteAndAddToList()
        addNote()
        setShapeRecyclerView()
    }

    // TODO: change dynamically layout item in recyclerView
    private fun initializeRecycleViewAdapter() = with(binding) {
       //App.preferenceHelper.putValue("isLinear", true)
       val isShapeRecyclerViewLinear = App.preferenceHelper.isLinearLayout("isLinear")

       if (isShapeRecyclerViewLinear){
           recycleView.layoutManager = LinearLayoutManager(requireContext())

       }
       else{
           recycleView.layoutManager = GridLayoutManager(requireContext(),2)

       }

       recycleView.adapter = adapter
    }

    private fun setShapeRecyclerView(){
        binding.shapeRecycleView.setOnClickListener {
            val isShapeRecyclerViewLinear = App.preferenceHelper.isLinearLayout("isLinear")

            if (isShapeRecyclerViewLinear){
                binding.recycleView.layoutManager = GridLayoutManager(requireContext(),2)
                App.preferenceHelper.removeValue("isLinear")
                App.preferenceHelper.putValue("isLinear", false)
                binding.shapeRecycleView.setBackgroundResource(R.drawable.ic_liniar_layout_shape)
                Toast.makeText(requireContext(), "Grid - $isShapeRecyclerViewLinear ", Toast.LENGTH_SHORT).show()

            }
            else{
                binding.recycleView.layoutManager = LinearLayoutManager(requireContext())
                App.preferenceHelper.removeValue("isLinear")
                App.preferenceHelper.putValue("isLinear", true)
                binding.shapeRecycleView.setBackgroundResource(R.drawable.ic_grid_layout_shape)
                Toast.makeText(requireContext(), "Linear - $isShapeRecyclerViewLinear", Toast.LENGTH_SHORT).show()

            }
        }
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
    }

    override fun onLongClick(model: NoteModel) {
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

    // TODO: change note
    override fun onShortClick(model: NoteModel) {
        App.preferenceHelper.putValue("changeNote", true)
        findNavController().navigate(
            R.id.action_noteAppMainFragment_to_noteAppDetailFragment

        )
    }
}