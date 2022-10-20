package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var textView1: TextView
    private lateinit var textView2: TextView
    private lateinit var linLayout: LinearLayout
    private lateinit var imageView1: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar2))
        //linLayout = findViewById(R.id.linearLayout)
        //showingitems()
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CustomRecyclerAdapter()

    }

    /*fun showingitems() {
        val ltInflater = layoutInflater
        for (i in 0 until 5) {
            val item: View = ltInflater.inflate(R.layout.element, linLayout, false)
            textView1 = item.findViewById(R.id.textView1)
            textView1.text = name[i].title
            textView2 = item.findViewById(R.id.textView1_1)
            textView2.text = name[i].subTitle
            imageView1 = item.findViewById(R.id.imageView1)
            val a = name[i].imagine
            val drawableResourceId =this.resources.getIdentifier("$a", "drawable", this.packageName)
            imageView1.setImageResource(drawableResourceId)
            item.getLayoutParams().width = FrameLayout.LayoutParams.MATCH_PARENT
            item.id = i
            item.setOnClickListener({ click(i) })
            linLayout.addView(item)
        }
    }*/
    fun click(i: Int, holder: CustomRecyclerAdapter.MyViewHolder) {
        val secondActi = Intent(holder.cont, MainActivity2::class.java)
        val text1 = name[i].title
        val text2 = name[i].subTitle
        secondActi.putExtra(MainActivity2.text1, text1)
        secondActi.putExtra(MainActivity2.text2, text2)
        secondActi.putExtra(MainActivity2.piture, name[i].imagine)
        startActivity(secondActi)
    }


}