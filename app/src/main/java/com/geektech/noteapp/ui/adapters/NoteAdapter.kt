package com.geektech.noteapp.ui.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geektech.noteapp.R
import com.geektech.noteapp.databinding.ItemNoteBinding
import com.geektech.noteapp.models.NoteModel

class NoteAdapter(
    private val onNoteLongClick:OnNoteClickListener,
    private val onNoteShortClick: OnNoteClickListener

):RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    private var noteList:List<NoteModel> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
     fun setList(list: List<NoteModel>){
        this.noteList = list
        notifyDataSetChanged()

    }

    class ViewHolder(item:View):RecyclerView.ViewHolder(item) {
        private val binding = ItemNoteBinding.bind(item)

        fun onBind(model: NoteModel,listener: OnNoteClickListener) {
            binding.itemTitle.text = model.title
            binding.itemDescription.text = model.description
            binding.itemData.text = model.date
            binding.itemTime.text = model.time
            binding.itemCardView.setCardBackgroundColor(Color.parseColor(model.backgroundColor))

            itemView.setOnLongClickListener {
                listener.onLongClick(model)
                return@setOnLongClickListener true
            }

            itemView.setOnClickListener {
                listener.onShortClick(model)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(noteList[position],onNoteLongClick)

    }

    override fun getItemCount(): Int {
        return noteList.size
    }
}

interface OnNoteClickListener{
    fun onLongClick(model: NoteModel)
    fun onShortClick(model: NoteModel)
}