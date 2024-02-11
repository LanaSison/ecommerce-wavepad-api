package com.example.wavepad

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class SignUpPage: AppCompatActivity() {

    private lateinit var emailedittext: EditText
    private lateinit var usernameeditext: EditText
    private lateinit var passwordedittext: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_page)

        emailedittext = findViewById(R.id.editTextEmail)
        usernameeditext = findViewById(R.id.editTextUsername)
        passwordedittext = findViewById(R.id.editTextPassword)

        val signUpButton: Button = findViewById(R.id.signUpButton)
        val textView: TextView = findViewById(R.id.Back)

        signUpButton.setOnClickListener {
            val email = emailedittext.text.toString().trim()
            val username = usernameeditext.text.toString().trim()
            val password = passwordedittext.text.toString().trim()

            if (email.isEmpty() || username.isEmpty() || password.isEmpty() ){
                return@setOnClickListener
            }
            signUp(email, username, password)
        }
        textView.setOnClickListener {
            val signUpIntent = Intent(this@SignUpPage, SignUpLogInPage::class.java)
            startActivity(signUpIntent)
        }
    }
    private fun signUp(username: String, email: String, password: String) {
        val apiManager = ApiManager()
        apiManager.signUp(username, email, password, object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    val signUpIntent = Intent(this@SignUpPage, HomePage::class.java)
                    startActivity(signUpIntent)
                } else { }
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
            }
        })
    }
}