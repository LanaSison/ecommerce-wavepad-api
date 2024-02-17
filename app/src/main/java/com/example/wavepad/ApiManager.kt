package com.example.wavepad

import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://localhost/127.0.0.1/api/signup/") // Replace with your API base URL
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(ApiService::class.java)

    fun signUp(username: String, email: String, password: String, callback: Callback<Void>) {
        val request = SignUpRequest(username, email, password)
        val call = apiService.signUp(request)
        call.enqueue(callback)
    }
}