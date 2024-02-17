package com.example.wavepad

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/signup")
    fun signUp(@Body request: SignUpRequest): Call<Void>
}