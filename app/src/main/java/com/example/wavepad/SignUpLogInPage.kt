package com.example.wavepad

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SignUpLogInPage: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_login_page)

        val signupbutton: Button = findViewById(R.id.signupbtn)
        val loginbutton: Button = findViewById(R.id.loginbtn)

        signupbutton.setOnClickListener {
            val signUpIntent = Intent(this@SignUpLogInPage, SignUpPage::class.java)
            startActivity(signUpIntent)
        }
        loginbutton.setOnClickListener {
            val signUpIntent = Intent(this@SignUpLogInPage, LoginPage::class.java)
            startActivity(signUpIntent)
        }

    }
}