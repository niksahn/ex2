package com.example.myapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide



class CustomRecyclerAdapter() :
    RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {
    var name: List<ListItemData> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class MyViewHolder(item: View) : RecyclerView.ViewHolder(item) {
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
        holder.title.text = name[position].name
        holder.subtitle.text ="type: " +name[position].species + ", status:" +name[position].status
        holder.itemView.setOnClickListener { click(position, holder) }

        Glide
            .with(holder.imageView1)
            .load(name[position].image)
            .into(holder.imageView1);
    }

    fun click(i: Int, holder: MyViewHolder) {
        val secondActi = Intent(holder.cont, MainActivity2::class.java)
        val text1 = name[i].name
        val text2 = " type: " +name[i].species + ", status:" +name[i].status
        secondActi.putExtra(MainActivity2.text1, text1)
        secondActi.putExtra(MainActivity2.text2, text2)
        secondActi.putExtra(MainActivity2.piture, name[i].image)
        holder.cont.startActivity(secondActi)
    }

    override fun getItemCount() = name.size
}
