package com.example.myapplication.Main.Veiw

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.myapplication.R


class  MainActivity2 : AppCompatActivity() {
    private lateinit var textView1: TextView
    private lateinit var textView2: TextView
    private lateinit var imageView1: ImageView
    private lateinit var Title: androidx.appcompat.widget.Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        textView2 = findViewById(R.id.textView)
        textView1 = findViewById(R.id.textVw1)
        imageView1 = findViewById(R.id.imageView)
        Title = findViewById(R.id.toolbar)
        show()
    }

    companion object {

        const val text1 = "text1"
        const val text2 = "text2"
        const val piture = "1"


    }


    fun retur(view: View) {
        finish()
    }

    fun show() {
        val inmage = intent.getStringExtra(piture)
        Glide
            .with(this)
            .load(inmage)
            .into(this.imageView1);
        val inttext1 = intent.getStringExtra(text1)
        val inttext2 = intent.getStringExtra(text2)
        textView1.text = inttext1
        textView2.text = inttext2
        Title.title = intent.getStringExtra(text1)
    }

}