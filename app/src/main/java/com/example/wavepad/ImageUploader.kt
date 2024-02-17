package com.example.wavepad

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class ImageUploader {

    private val retrofitService = RetrofitClient.instance

    fun uploadImage(imageFile: File, description: String, callback: (Boolean, String) -> Unit) {
        val requestFile = imageFile.asRequestBody("image/*".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("image", imageFile.name, requestFile)
        val desc = description.toRequestBody("text/plain".toMediaTypeOrNull())

        val call = retrofitService.uploadImage(body)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    callback(true, "Image uploaded successfully")
                } else {
                    callback(false, "Failed to upload image: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                callback(false, "Network error: ${t.message}")
            }
        })
    }
}
