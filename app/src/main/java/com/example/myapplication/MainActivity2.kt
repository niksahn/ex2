package com.example.myapplication

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity2 : AppCompatActivity() {
    private lateinit var textView1: TextView
    private lateinit var textView2: TextView
    private lateinit var imageView1: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        textView2 = findViewById(R.id.textView)
        textView1 = findViewById(R.id.textVw1)
        show()
    }

    companion object {

        const val text1 = "text1"
        const val text2 = "text2"


    }

    fun retur(view: View) {
        val firstActi = Intent(this, MainActivity::class.java)
        startActivity(firstActi)
    }

    fun show() {

        // Get the count from the intent extras
        var inent = Intent(this, MainActivity::class.java)

         //var bitmap  : Bitmap = inent.getParcelableExtra("BitmapImage")!!
        imageView1.setImageBitmap(inent.getParcelableExtra("BitmapImage"))


        val inttext1 = intent.getStringExtra(text1)
        val inttext2 = intent.getStringExtra(text2)
        textView1.text = inttext1
        textView2.text = inttext2


    }

}