package com.example.wavepad

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProductFullDetail: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_full_detail)

        val imageView: ImageView = findViewById(R.id.returnback)

        imageView.setOnClickListener {
            val signUpIntent = Intent(this@ProductFullDetail, HomePage::class.java)
            startActivity(signUpIntent)

            Log.d("ProductFullDetail", "ProductFullDetail activity created")
        }
    }
}