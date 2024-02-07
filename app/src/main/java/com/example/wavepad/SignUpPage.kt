package com.example.wavepad

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SignUpPage: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_page)
        val signUpButton: Button = findViewById(R.id.signUpButton)
        val textView: TextView = findViewById(R.id.Back)

        signUpButton.setOnClickListener {
            val signUpIntent = Intent(this@SignUpPage, HomePage::class.java)
            startActivity(signUpIntent)
        }

        textView.setOnClickListener {
            val signUpIntent = Intent(this@SignUpPage, SignUpLogInPage::class.java)
            startActivity(signUpIntent)
        }
    }
}