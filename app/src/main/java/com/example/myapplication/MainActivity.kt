package com.example.myapplication
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
class MainActivity : AppCompatActivity() {
    private var name = arrayOf(
        "Ели", "Мужики", "Запивали", "Конюх", "Они"

    )
    private var position = arrayOf(
        "Мясо", "Пивом", "О чем", "Говорил", "Не понимали"
    )
    private var images = arrayOf(
        "img", "img", "img", "img", "img"
    )
    private lateinit var title: TextView


    private lateinit var textView1: TextView
    private lateinit var textView2: TextView
    private lateinit var linLayout: LinearLayout
    private lateinit var imageView1: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar2))
        linLayout = findViewById(R.id.linearLayout)
        showingitems()

    }
    fun showingitems(){
        val ltInflater = layoutInflater
        for (i in 0 until name.size) {
            val item: View = ltInflater.inflate(R.layout.element, linLayout, false)
            textView1 = item.findViewById(R.id.textView1)
            textView1.text = name[i]
            textView1.id = (i + 1) * 103
            textView1.setOnClickListener({ click(it) })
            textView2 = item.findViewById(R.id.textView1_1)
            textView2.setOnClickListener({ click(it) })
            textView2.text = position[i]
            textView2.id = (i + 1) * 107
            imageView1 = item.findViewById(R.id.imageView1)
            val a = images[i]
            val drawableResourceId =
                this.resources.getIdentifier("$a", "drawable", this.packageName)
            imageView1.setImageResource(drawableResourceId)
            item.getLayoutParams().width = FrameLayout.LayoutParams.MATCH_PARENT
            item.id = i
            item.setOnClickListener({ click(it) })
            linLayout.addView(item)
        }
    }

    fun click(view: View) {
        val idframe: Int
        if (view.id <= name.size) {
            idframe = view.id
        } else if (view.id % 103 == 0) idframe = view.id / 103 - 1
        else idframe = view.id / 107 - 1

        val secondActi = Intent(this, MainActivity2::class.java)
        val text1 = name[idframe]
        val text2 = position[idframe]
        secondActi.putExtra(MainActivity2.text1, text1)
        secondActi.putExtra(MainActivity2.text2, text2)

        secondActi.putExtra(MainActivity2.piture, images[idframe])
        try {
            //if (view.id!=null and view.id )
            title = findViewById(view.id)
            secondActi.putExtra(MainActivity2.titlee, title.text)

        } catch (e: Exception) {
        }
        startActivity(secondActi)
    }
}