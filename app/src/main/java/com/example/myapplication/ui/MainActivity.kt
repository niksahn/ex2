package com.example.myapplication.ui


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.examplet.ui.theme.ExampleTTheme
import com.ramcosta.composedestinations.DestinationsNavHost


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExampleTTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DestinationsNavHost(
                        modifier = Modifier
                            .fillMaxSize(),
                        navGraph = NavGraphs.root
                    )
                }
            }
        }
    }
}
/*    val adapter = CustomRecyclerAdapter()
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
    } */





