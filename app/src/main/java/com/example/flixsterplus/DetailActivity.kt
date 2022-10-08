package com.example.flixsterplus

import android.media.Image
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var posterView = findViewById<ImageView>(R.id.posterImageView)
        var popularityTextView = findViewById<TextView>(R.id.popularityTextView)
        var seriesTitleTextView = findViewById<TextView>(R.id.seriesTitleTextView)
        var overviewTextView = findViewById<TextView>(R.id.overviewTextView)

        //val tvSeries = intent.getSerializableExtra(tv_extra) as Television


    }

}