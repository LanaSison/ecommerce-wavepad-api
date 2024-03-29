//package com.example.wavepad
//
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Button
//import android.widget.EditText
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//class SignUpPage: AppCompatActivity() {
//
//    private lateinit var emailedittext: EditText
//    private lateinit var usernameeditext: EditText
//    private lateinit var passwordedittext: EditText
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.signup_page)
//
//        emailedittext = findViewById(R.id.editTextEmail)
//        usernameeditext = findViewById(R.id.editTextUsername)
//        passwordedittext = findViewById(R.id.editTextPassword)
//
//        val signUpButton: Button = findViewById(R.id.signUpButton)
//        val textView: TextView = findViewById(R.id.Back)
//
//        signUpButton.setOnClickListener {
//            val email = emailedittext.text.toString().trim()
//            val username = usernameeditext.text.toString().trim()
//            val password = passwordedittext.text.toString().trim()
//
//            if (email.isEmpty() || username.isEmpty() || password.isEmpty() ){
//                return@setOnClickListener
//            }
//            signUp(email, username, password)
//        }
//        textView.setOnClickListener {
//            val signUpIntent = Intent(this@SignUpPage, SignUpLogInPage::class.java)
//            startActivity(signUpIntent)
//        }
//    }
//    private fun signUp(username: String, email: String, password: String) {
//        val apiManager = ApiManager()
//        apiManager.signUp(username, email, password, object : Callback<Void> {
//            override fun onResponse(call: Call<Void>, response: Response<Void>) {
//                if (response.isSuccessful) {
//                    val signUpIntent = Intent(this@SignUpPage, HomePage::class.java)
//                    startActivity(signUpIntent)
//                } else { }
//            }
//            override fun onFailure(call: Call<Void>, t: Throwable) {
//            }
//        })
//    }
//}
package com.example.wavepad

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wavepad.R
import com.example.wavepad.SignUpLogInPage
import com.example.wavepad.User
import com.example.wavepad.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpPage : AppCompatActivity() {

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

            if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                // Handle empty fields
                return@setOnClickListener
            }
            signUp(username, email, password)
        }

        textView.setOnClickListener {
            val signUpIntent = Intent(this@SignUpPage, SignUpLogInPage::class.java)
            startActivity(signUpIntent)
        }
    }

    private fun signUp(username: String, email: String, password: String) {
        // Set default values for parameters not available in the signup page
        val age = 0 // Or any default value suitable for your application
        val gender = "" // Or any default value suitable for your application
        val mobile = "" // Or any default value suitable for your application
        val status = 1 // Or any default value suitable for your application

        val apiService = RetrofitClient.apiService
        val call = apiService.registerUser(User(username, email, password, mobile, age, gender, status))

        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    // Handle successful response
                    val signUpIntent = Intent(this@SignUpPage, SignUpLogInPage::class.java)
                    startActivity(signUpIntent)
                    finish() // Finish current activity to prevent going back
                } else {
                    // Handle unsuccessful response
                    val statusCode = response.code() // Get the HTTP status code
                    val errorMessage = when (statusCode) {
                        // Add more cases as needed for different status codes
                        404 -> "Resource not found"
                        else -> "Response error: $statusCode" // Default error message
                    }
                    Toast.makeText(this@SignUpPage, errorMessage, Toast.LENGTH_SHORT).show()

                    // Optionally, inspect the response body for more details
                    val errorBody = response.errorBody()?.string()
                    if (!errorBody.isNullOrEmpty()) {
                        // Parse and display additional error details if available
                        // Example: Toast.makeText(this@SignUpPage, errorBody, Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                // Handle network-related failures
                val errorMessage = "Network request failed: ${t.message}"
                Toast.makeText(this@SignUpPage, errorMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}

//        call.enqueue(object : Callback<UserResponse> {
//            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
//                if (response.isSuccessful) {
//                    val signUpIntent = Intent(this@SignUpPage, SignUpLogInPage::class.java)
//                    startActivity(signUpIntent)
//                    finish() // Finish current activity to prevent going back
//                } else {
//                    // Display a toast indicating response error
//                    Toast.makeText(this@SignUpPage, "Response error", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
//                // Display a toast indicating the specific failure reason
//                val errorMessage = "Network request failed: ${t.message}"
//                Toast.makeText(this@SignUpPage, errorMessage, Toast.LENGTH_SHORT).show()
//            }
//
//        })

