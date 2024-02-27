package com.example.wavepad

import com.google.gson.annotations.SerializedName
data class UserResponse(
    @SerializedName("message") val message: String,
    @SerializedName("status") val status: String,
    @SerializedName("user") val user: User
)
