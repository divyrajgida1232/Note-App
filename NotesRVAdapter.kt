package com.example.notesapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.ArrayList

class NotesRVAdapter(private val context: Context, private val listener: INotesRVAdapter)
    : RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder>() {
    private val allTask = ArrayList<Note>()

    inner class NoteViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val taskText:TextView = itemView.findViewById<TextView>(R.id.tvTaskText)
        val deleteTask:ImageView = itemView.findViewById<ImageView>(R.id.btnDeleteTask)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {

        val viewHolder = NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note,parent,false))
        viewHolder.deleteTask.setOnClickListener{
            listener.onItemClicked((allTask[viewHolder.adapterPosition]))
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return allTask.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = allTask[position]
        holder.taskText.text = currentNote.text
    }

    fun updateList(newList : List<Note>){
        allTask.clear()
        allTask.addAll(newList)

        notifyDataSetChanged()
    }
}

interface INotesRVAdapter {
    fun onItemClicked(note: Note)
}