package com.example.wavepad

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AccountPage: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.account_page)

        val textview: TextView = findViewById(R.id.register)

        textview.setOnClickListener {
            val signUpIntent = Intent(this@AccountPage, RegisterAsSellerPage::class.java)
            startActivity(signUpIntent)
        }
    }
}