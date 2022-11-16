package com.example.myapplication.Main.Veiw


import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.Button
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Main.Adapter.CustomRecyclerAdapter
import com.example.myapplication.Main.VeiwModel.MyViewModel
import com.example.myapplication.R

val adapter = CustomRecyclerAdapter()

class MainActivity : AppCompatActivity() {
    private val viewModel : MyViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {


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

