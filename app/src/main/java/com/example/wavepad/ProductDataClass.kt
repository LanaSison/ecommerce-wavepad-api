package com.example.wavepad

import java.io.Serializable

data class ProductDataClass(
    val id: Int,
    val imageResource: Int,
    val title: String,
    val author: String,
    val categories: String,
    val price: String,
    val description: String
):Serializable
