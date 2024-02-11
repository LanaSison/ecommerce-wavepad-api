package com.example.wavepad

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginPage: AppCompatActivity() {

    private lateinit var  edittextemail: EditText
    private lateinit var  edittextpassword: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)

        edittextemail = findViewById(R.id.editTextEmail)
        edittextpassword = findViewById(R.id.editTextPassword)

        val loginButton: Button = findViewById(R.id.loginButton)
        val textView: TextView = findViewById(R.id.Back)

        loginButton.setOnClickListener {

            val email = edittextemail.text.toString().trim()
            val password = edittextpassword.text.toString().trim()

            performLogin(email, password)
        }
        textView.setOnClickListener {
            onBackPressed()
        }
    }
    private fun performLogin(email: String, password: String) {
        // Here you can add your logic to perform the login.
        // Typically, you'll make an API call to your backend server to authenticate the user.
        // If login is successful, navigate to the home screen. Otherwise, show an error message.

        // For demonstration purposes, let's assume the login is successful and navigate to the home screen
        val intent = Intent(this, HomePage::class.java)
        startActivity(intent)
        finish() // Finish this activity so that user cannot navigate back to the login screen using the back button
    }
}
