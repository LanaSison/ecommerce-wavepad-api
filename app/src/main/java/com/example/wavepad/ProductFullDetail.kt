package com.example.wavepad

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProductFullDetail: AppCompatActivity() {
    private var quantity: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_full_detail)

        val buyNowButton: Button = findViewById(R.id.BuyNow)
        val quantityTextView: TextView = findViewById(R.id.text_quantity)
        val minusButton: ImageButton = findViewById(R.id.btn_minus)
        val plusButton: ImageButton = findViewById(R.id.btn_plus)
        val productNameTextView: TextView = findViewById(R.id.text_product_name)
        val productPriceTextView: TextView = findViewById(R.id.text_product_price)
        val productDescriptionTextView: TextView = findViewById(R.id.descriptiontext)
        val productImageView: ImageView = findViewById(R.id.image_product)

        val product = intent.getSerializableExtra("PRODUCT") as? ProductDataClass
        product?.let {
            productNameTextView.text = it.title
            productPriceTextView.text = it.price
            productDescriptionTextView.text = it.description
            productImageView.setImageResource(it.imageResource)
        }

        quantityTextView.text = "$quantity"

        minusButton.setOnClickListener {
            if (quantity > 1) {
                quantity--
                quantityTextView.text = "$quantity"
            }
        }

        plusButton.setOnClickListener {
            quantity++
            quantityTextView.text = "$quantity"
        }

        buyNowButton.setOnClickListener {
            // Create intent to navigate to CheckoutPage
            val checkoutIntent = Intent(this@ProductFullDetail, CheckOutPage::class.java).apply {
                putExtra("PRODUCT", product)
                putExtra("QUANTITY", quantity)
            }
            startActivity(checkoutIntent)

            Log.d("ProductFullDetail", "Navigating to CheckoutPage")
        }
    }
}
