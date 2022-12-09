package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMisTareasBinding
import com.squareup.picasso.Picasso

class MyTasks : AppCompatActivity() {

    private lateinit var binding : ActivityMisTareasBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMisTareasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Picasso.get().load("https://img.freepik.com/premium-photo/colored-pencils-sheet-notes-lie-blue-background-top-view-background-todo-list-back-school_230115-2414.jpg?w=2000").into(binding.imageView2)

        //Creamos un layout managger.
        binding.rvMain.layoutManager = LinearLayoutManager(this)
        binding.rvMain.adapter  = TaskAdapter(getStrList())
    }

    private fun getStrList():List<String>{
        var listTask = mutableListOf<String>()
        for (i in 1 ..10) listTask.add("Mi tarea número: $i")
        return listTask
    }


    companion object{
        const val KEY_MESSAGE = "com.example.todolist.mensaje1"
    }
}