package com.example.wavepad

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductService {

    private val api: ProductApi = RetrofitClient.createService(ProductApi::class.java)

    fun getProducts(callback: (List<ProductDataClass>?) -> Unit) {
        api.getProducts().enqueue(object : Callback<List<ProductDataClass>> {
            override fun onResponse(call: Call<List<ProductDataClass>>, response: Response<List<ProductDataClass>>) {
                if (response.isSuccessful) {
                    val products = response.body()
                    callback(products)
                } else {
                    // Handle unsuccessful response
                    callback(null)
                }
            }

            override fun onFailure(call: Call<List<ProductDataClass>>, t: Throwable) {
                // Handle network errors
                callback(null)
            }
        })
    }
}
