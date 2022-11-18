package com.example.myapplication.Main.Veiw

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.myapplication.R


class DetailsActivity : AppCompatActivity() {
    private lateinit var textView1: TextView
    private lateinit var textView2: TextView
    private lateinit var imageView1: ImageView
    private lateinit var title: androidx.appcompat.widget.Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        textView2 = findViewById(R.id.textView)
        textView1 = findViewById(R.id.textVw1)
        imageView1 = findViewById(R.id.imageView)
        title = findViewById(R.id.toolbar)
        title.setOnClickListener {   returnTo() }
        show()
    }

    companion object {

        const val text1 = "text1"
        const val text2 = "text2"
        const val picture = "1"


    }


    private fun returnTo() {
        finish()
    }

    private fun show() {
        val imagine = intent.getStringExtra(picture)
        Glide
            .with(this)
            .load(imagine)
            .into(this.imageView1);
        val inttext1 = intent.getStringExtra(text1)
        val inttext2 = intent.getStringExtra(text2)
        textView1.text = inttext1
        textView2.text = inttext2
        title.title = intent.getStringExtra(text1)
    }

}