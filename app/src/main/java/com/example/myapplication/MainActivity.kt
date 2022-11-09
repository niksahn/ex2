package com.example.myapplication

import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlin.concurrent.thread

val adapter = CustomRecyclerAdapter()

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MyViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        val db = Room.databaseBuilder(
            applicationContext ,
            AppDatabase::class.java, "todo-list.db"
        ).build()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar2))
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
        val button = findViewById<Button>(R.id.button)
        recyclerView.adapter = adapter



        viewModel.name.observe(this) {
            it?.let {
                adapter.name = it
                db.personDao.insertUser(it)
            }
        }
        button.setOnClickListener {
            viewModel.name.observe(this@MainActivity) {
                it?.let {
                    adapter.name = it
                    adapter.string=autoCompleteTextView.text.toString()
                }
            }
        }
thread {  println(db.personDao.getUsers())}
        println("llooooooooooool")
    }

}





/* autoCompleteTextView.onItemClickListener = AdapterView.OnItemClickListener { parent, _,
                                                                                     position, id ->
            autoCompleteTextView.text= parent.getItemAtPosition(position) as Editable?
        }
history.add(autoCompleteTextView.text.toString())
            if (history.size>6 ){history.removeAt(0)}
            val histadapter: ArrayAdapter<String> =
                ArrayAdapter<String>(this@MainActivity, R.layout.activity_main,R.id.autoCompleteTextView, history)
            autoCompleteTextView.setAdapter(histadapter)*/

