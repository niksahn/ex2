package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private  var name = arrayOf(
        "Ели","Мужики","Запивали","Конюх","Они"

    )
    private  var position = arrayOf(
        "Мясо","Пивом","О чем","Говорил","Не понимали"
    )
    private   var images= arrayOf (
        "img","img","img","img","img"
    )
    /*private lateinit var textView1: TextView
    private lateinit var textView2: TextView
    private lateinit var title: TextView
    private lateinit var imageView1: ImageView*/
    private lateinit var textView1: TextView
    private lateinit var textView2: TextView
    private lateinit var linLayout: LinearLayout
    private lateinit var imageView1: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar2))
        linLayout = findViewById(R.id.linearLayout)
        val ltInflater = layoutInflater
        for (i in 0 until name.size) {
            val item: View = ltInflater.inflate(R.layout.element, linLayout, false)
            textView1 = item.findViewById(R.id.textView1)
            textView1.text = name[i]
            textView2 = item.findViewById(R.id.textView1_1)
            textView2.text = position[i]
            imageView1 = item.findViewById(R.id.imageView1)
            val a=images[i]
            val drawableResourceId =this.resources.getIdentifier("$a", "drawable", this.packageName)
            imageView1.setImageResource(drawableResourceId)
            item.getLayoutParams().width = FrameLayout.LayoutParams.MATCH_PARENT
            linLayout.addView(item)
        }

    }

    /*fun click(view: View) {
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
        try {
            //if (view.id!=null and view.id )
            title = findViewById(view.id)
            secondActi.putExtra(MainActivity2.titlee, title.text)

        } catch (e: Exception) {
        }
        startActivity(secondActi)
    }

    fun click5(view: View) {
        textView1 = findViewById(R.id.textView5)
        textView2 = findViewById(R.id.textView5_1)
        imageView1 = findViewById(R.id.imageView5)
        click(view)
    }

    fun click4(view: View) {
        textView1 = findViewById(R.id.textView4)
        textView2 = findViewById(R.id.textView4_1)
        imageView1 = findViewById(R.id.imageView4)
        click(view)
    }

    fun click3(view: View) {
        textView1 = findViewById(R.id.textView3)
        textView2 = findViewById(R.id.textView3_1)
        imageView1 = findViewById(R.id.imageView3)
        click(view)
    }

    fun click2(view: View) {
        textView1 = findViewById(R.id.textView2)
        textView2 = findViewById(R.id.textView2_1)
        imageView1 = findViewById(R.id.imageView2)
        click(view)
    }

    fun click1(view: View) {
        textView1 = findViewById(R.id.textView1)
        textView2 = findViewById(R.id.textView1_1)
        imageView1 = findViewById(R.id.imageView1)
        click(view)
    }*/
}