package com.example.myapplication
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private val ViewModel by lazy {ViewModelProvider(this).get(MyViewModel::class.java)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar2))
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter =  CustomRecyclerAdapter()


        recyclerView.adapter = adapter
        //val model = ViewModelProvider(this).get(MyViewModel::class.java)
        ViewModel.getList().observe(this, Observer {
            it?.let {
                adapter.refreshUsers(it)
            }
        })
        //model.execute()
    }

}