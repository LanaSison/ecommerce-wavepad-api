package com.example.wavepad


//ProductApi Interface:
//This interface defines the API endpoints using Retrofit annotations.
//In this case, there's only one endpoint, getProducts(), which fetches a list of products.
//The Companion object inside the interface provides a factory method create() to instantiate the Retrofit interface implementation.

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.Call

interface ProductApi {
    @GET("api/products")
    fun getProducts(): Call<List<ProductDataClass>>

    companion object {
        private const val BASE_URL = "http://your_base_url_here/"

        fun create(): ProductApi {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ProductApi::class.java)
        }
    }
}
