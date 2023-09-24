package com.example.tugas2.ui.skill

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.tugas2.R
import org.w3c.dom.Text

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val skill = intent.getParcelableExtra<Skill>("skill")
        if(skill != null){
            val textView: TextView = findViewById(R.id.detailActivityTv)
            val imageView: ImageView = findViewById(R.id.detailActivityIv)

            textView.text= skill.heading
            imageView.setImageResource(skill.titleImage)
        }
    }
}