package com.example.myapplication
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MyViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar2))
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = CustomRecyclerAdapter("")
        val editText = findViewById<EditText>(R.id.editTextTextPersonName)
        recyclerView.adapter = adapter
        viewModel.name.observe(this) {
            it?.let {
                adapter.name = it
            }
        }

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val adapter = CustomRecyclerAdapter(s.toString())
                recyclerView.adapter = adapter
                viewModel.name.observe(this@MainActivity) {
                    it?.let {
                        adapter.name = it
                        println( adapter.name)
                    }
                }

                
            }

            override fun afterTextChanged(s: Editable) {}
        })
        

    }

}