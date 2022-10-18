package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide;

class CustomRecyclerAdapter() :
    RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val itemView=item
        val title: TextView =  item.findViewById(R.id.textView1)
        val subtitle: TextView = item.findViewById(R.id.textView1_1)
        val imageView1: ImageView =item.findViewById(R.id.imageView1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.element, parent, false)
        return MyViewHolder(item)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = name[position].title
        holder.subtitle.text = name[position].subTitle

        Glide
            .with(holder.imageView1)
            .load(name[position].imagine)
            .into(holder.imageView1);

    }

    override fun getItemCount() = name.size
}
val drawableResourceId =this.resources.getIdentifier("$a", "drawable", this.packageName)