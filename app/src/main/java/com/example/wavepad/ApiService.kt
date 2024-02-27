//package com.example.wavepad
//
//import retrofit2.Call
//import retrofit2.http.Body
//import retrofit2.http.POST
//
//interface ApiService {
//    @POST("api/signup")
//    fun signUp(@Body request: SignUpRequest): Call<Void>
//}

import com.example.wavepad.ChangePasswordRequest
import com.example.wavepad.LoginRequest
import com.example.wavepad.User
import com.example.wavepad.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("register")
    fun registerUser(@Body user: User): Call<UserResponse>

    @POST("login")
    fun loginUser(@Body loginRequest: LoginRequest): Call<UserResponse>

    @POST("logout")
    fun logoutUser(): Call<Void>

    @POST("change")
    fun changePassword(@Body changePasswordRequest: ChangePasswordRequest): Call<Void>

    @GET("get-u")
    fun getUserData(): Call<UserResponse>
}
