package com.example.testapplication.utils

import android.widget.ImageView
import com.example.testapplication.R

 fun setIcon(imageView: ImageView, platform: String) {
    when (platform) {
        "Sercomm G450" -> {
            imageView.setImageResource(R.drawable.vera_plus_big)
        }

        "Sercomm G550" -> {
            imageView.setImageResource(R.drawable.vera_secure_big)
        }

        "MiCasaVerde VeraLite", "Sercomm NA900", "Sercomm NA301", "Sercomm NA930", "" -> {
            imageView.setImageResource(R.drawable.vera_edge_big)
        }
    }
}