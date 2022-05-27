package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), INotesRVAdapter {

    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_main)

        supportActionBar!!.title = "My Tasks"

        rvTask.layoutManager = LinearLayoutManager(this)
        val adapter  = NotesRVAdapter(this,this)
        rvTask.adapter = adapter

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allTask.observe(this, Observer { list->
            list?.let {
                adapter.updateList(it)
            }
        })
    }

    override fun onItemClicked(note: Note) {
        viewModel.deleteTask(note)
        Toast.makeText(this , " ${note.text} Completed",Toast.LENGTH_SHORT).show()
    }

    fun submitData(view: View) {
        val noteText = etTask.text.toString()
        if (noteText.isNotEmpty()){
            viewModel.insertTask(Note(noteText))
            Toast.makeText(this , "$noteText Added",Toast.LENGTH_SHORT).show()
        }
    }
}