package com.example.myapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CustomRecyclerAdapter() :
    RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val itemView: FrameLayout = item.findViewById(R.id.FrameLayout1)
        val cont = item.context
        val title: TextView = item.findViewById(R.id.textView1)
        val subtitle: TextView = item.findViewById(R.id.textView1_1)
        val imageView1: ImageView = item.findViewById(R.id.imageView1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.element, parent, false)

        return MyViewHolder(item)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = name[position].title
        holder.subtitle.text = name[position].subTitle
        holder.itemView.setOnClickListener { click(position, holder) }
        val a = name[position].imagine
        val drawableResourceId =
            holder.cont.resources.getIdentifier("$a", "drawable", holder.cont.packageName)
        Glide
            .with(holder.imageView1)
            .load(drawableResourceId)
            .into(holder.imageView1);
    }

    fun click(i: Int, holder: MyViewHolder) {
        val secondActi = Intent(holder.cont, MainActivity2::class.java)
        val text1 = name[i].title
        val text2 = name[i].subTitle
        secondActi.putExtra(MainActivity2.text1, text1)
        secondActi.putExtra(MainActivity2.text2, text2)
        secondActi.putExtra(MainActivity2.piture, name[i].imagine)
        holder.cont.startActivity(secondActi)
    }

    override fun getItemCount() = name.size
}
