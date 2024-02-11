package com.example.wavepad

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class CartPage: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chat_page)

        val imageView: ImageView = findViewById(R.id.returnback)

        imageView.setOnClickListener {
            val signUpIntent = Intent(this@CartPage, HomePage::class.java)
            startActivity(signUpIntent)
        }
    }
}