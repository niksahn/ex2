package com.example.myapplication

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity


class MainActivity2 : AppCompatActivity() {
    private lateinit var textView1: TextView
    private lateinit var textView2: TextView
    private lateinit var imageView1: ImageView
    private lateinit var Title:androidx.appcompat.widget.Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        textView2 = findViewById(R.id.textView)
        textView1 = findViewById(R.id.textVw1)
        imageView1 = findViewById(R.id.imageView)
        Title=findViewById(R.id.toolbar)
        show()
    }

    companion object {

        const val text1 = "text1"
        const val text2 = "text2"
        const val titlee = "title"


    }

    fun retur(view: View) {
        val firstActi = Intent(this, MainActivity::class.java)
        startActivity(firstActi)
    }

    fun show() {

        // Get the count from the intent extras
        var inent = Intent(this, MainActivity::class.java)
        val extras = intent.extras
        val byteArray = extras!!.getByteArray("picture")
        val bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray!!.size)
         //var bitmap  : Bitmap = inent.getParcelableExtra("BitmapImage")!!
        imageView1.setImageBitmap(bmp)


        val inttext1 = intent.getStringExtra(text1)
        val inttext2 = intent.getStringExtra(text2)

        textView1.text = inttext1
        textView2.text = inttext2
        Title.title=intent.getStringExtra(titlee)
    }

}