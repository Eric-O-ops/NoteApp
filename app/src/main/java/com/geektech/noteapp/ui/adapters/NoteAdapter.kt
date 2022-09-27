package com.geektech.noteapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geektech.noteapp.R
import com.geektech.noteapp.databinding.ItemNoteBinding
import com.geektech.noteapp.models.NoteModel

class NoteAdapter(
    private val noteList:ArrayList<NoteModel>
):RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    class ViewHolder(item:View):RecyclerView.ViewHolder(item) {
        private val binding = ItemNoteBinding.bind(item)

        fun onBind(model: NoteModel) {
            binding.itemTitle.text = model.title
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(noteList[position])
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

}