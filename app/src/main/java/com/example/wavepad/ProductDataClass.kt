package com.example.wavepad

import java.io.Serializable

data class ProductDataClass(
    val title: String,
    val author: String,
    val genre: String,
    val price: String,
    val imageResource: Int
):Serializable
