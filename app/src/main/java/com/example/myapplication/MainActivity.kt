package com.example.myapplication

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.ByteArrayOutputStream


class MainActivity : AppCompatActivity() {
    private lateinit var textView1: TextView
    private lateinit var textView2: TextView
    private lateinit var imageView1: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun click() {
        val bitmap = (imageView1.getDrawable() as BitmapDrawable).bitmap
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val byteArray: ByteArray = stream.toByteArray()
        val secondActi = Intent(this, MainActivity2::class.java)
        val text1 = textView1.text.toString()
        val text2 = textView2.text.toString()
        secondActi.putExtra(MainActivity2.text1, text1)
        secondActi.putExtra(MainActivity2.text2, text2)
        secondActi.putExtra("picture", byteArray)
        startActivity(secondActi)
    }

    fun click5(view: View,textView: TextView) {
        print(view.getId())
        textView1 = findViewById(textView.getId())
        textView2 = findViewById(textView.getId())
        imageView1 = findViewById(R.id.imageView5)
        click()
    }

    fun click4(view: View) {
        textView1 = findViewById(R.id.textView4)
        textView2 = findViewById(R.id.textView4_1)
        imageView1 = findViewById(R.id.imageView4)
        click()
    }

    fun click3(view: View) {
        textView1 = findViewById(R.id.textView3)
        textView2 = findViewById(R.id.textView3_1)
        imageView1 = findViewById(R.id.imageView3)
        click()
    }

    fun click2(view: View) {
        textView1 = findViewById(R.id.textView2)
        textView2 = findViewById(R.id.textView2_1)
        imageView1 = findViewById(R.id.imageView2)
        click()
    }

    fun click1(view: View) {
        textView1 = findViewById(R.id.textView1)
        textView2 = findViewById(R.id.textView1_1)
        imageView1 = findViewById(R.id.imageView1)
        click()
    }
}