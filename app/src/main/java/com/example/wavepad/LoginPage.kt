package com.example.wavepad

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginPage: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)

        val loginButton: Button = findViewById(R.id.loginButton)
        val textView: TextView = findViewById(R.id.Back)

        loginButton.setOnClickListener {
            val signUpIntent = Intent(this@LoginPage, HomePage::class.java)
            startActivity(signUpIntent)
        }

        textView.setOnClickListener {
            val signUpIntent = Intent(this@LoginPage, SignUpLogInPage::class.java)
            startActivity(signUpIntent)
        }
    }
}