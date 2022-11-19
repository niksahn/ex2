package com.example.myapplication.main.veiw


import android.content.Intent
import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Main.Adapter.CustomRecyclerAdapter
import com.example.myapplication.main.veiwModel.MyViewModel
import com.example.myapplication.R
import com.example.myapplication.data.model.ListItemData
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    val adapter = CustomRecyclerAdapter()
    private val viewModel: MyViewModel by viewModel()
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
                adapter.click = ::clickItem
            }
        }
        button.setOnClickListener {
            viewModel.name.observe(this@MainActivity) {
                it?.let {
                    adapter.name = it
                    adapter.string = autoCompleteTextView.text.toString()
                    adapter.click = ::clickItem

                }
            }
        }


    }

    private fun clickItem(
        i: Int,
        holder: CustomRecyclerAdapter.MyViewHolder,
        name: List<ListItemData>
    ) {
        val secondActi = Intent(holder.cont, DetailsActivity::class.java)
        val text1 = name[i].name
        val text2 = " type: " + name[i].species + ", status:" + name[i].status
        secondActi.putExtra(DetailsActivity.text1, text1)
        secondActi.putExtra(DetailsActivity.text2, text2)
        secondActi.putExtra(DetailsActivity.picture, name[i].image)
        holder.cont.startActivity(secondActi)
    }

}





