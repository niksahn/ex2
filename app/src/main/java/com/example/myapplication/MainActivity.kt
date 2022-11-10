package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.os.Build

import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import java.time.Instant


val adapter = CustomRecyclerAdapter()
class MainActivity : AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {


        val mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        //val editor: SharedPreferences.Editor = mSettings.edit()
        //if (!mSettings.contains(APP_PREFERENCES_TIME))editor.putString(APP_PREFERENCES_TIME, Instant. now().toString())



        val db = Room.databaseBuilder(
            applicationContext ,
            AppDatabase::class.java, "pers.db"
        ).build()
        val viewModel=MyViewModel(mSettings,db)
        //val viewModel by viewModels<MyViewModel>()

        /*viewModel.db=db
        viewModel.mSettings=mSettings*/
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
                //db.personDao.insertUser(it)
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


}}





/* autoCompleteTextView.onItemClickListener = AdapterView.OnItemClickListener { parent, _,
                                                                                     position, id ->
            autoCompleteTextView.text= parent.getItemAtPosition(position) as Editable?
        }
history.add(autoCompleteTextView.text.toString())
            if (history.size>6 ){history.removeAt(0)}
            val histadapter: ArrayAdapter<String> =
                ArrayAdapter<String>(this@MainActivity, R.layout.activity_main,R.id.autoCompleteTextView, history)
            autoCompleteTextView.setAdapter(histadapter)*/

