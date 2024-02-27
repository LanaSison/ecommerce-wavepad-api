package com.example.wavepad

import com.google.gson.annotations.SerializedName
data class ChangePasswordRequest(
    @SerializedName("password") val password: String,
    @SerializedName("password_confirmation") val passwordConfirmation: String
)
