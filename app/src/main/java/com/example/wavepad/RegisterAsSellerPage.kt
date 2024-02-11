package com.example.wavepad

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RegisterAsSellerPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_as_seller_page)

        val imageView: ImageView = findViewById(R.id.returnback)

        imageView.setOnClickListener {
            val signUpIntent = Intent(this@RegisterAsSellerPage, AccountPage::class.java)
            startActivity(signUpIntent)
        }

        val spinnerDay = findViewById<Spinner>(R.id.spinnerDay)
        val spinnerMonth = findViewById<Spinner>(R.id.spinnerMonth)
        val spinnerYear = findViewById<Spinner>(R.id.spinnerBirthYear)

        val days = resources.getStringArray(R.array.days)
        val months = resources.getStringArray(R.array.months)
        val years = resources.getStringArray(R.array.years)

        val adapterDay = ArrayAdapter(this, android.R.layout.simple_spinner_item, days)
        val adapterMonth = ArrayAdapter(this, android.R.layout.simple_spinner_item, months)
        val adapterYear = ArrayAdapter(this, android.R.layout.simple_spinner_item, years)

        adapterDay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerDay.adapter = adapterDay
        spinnerMonth.adapter = adapterMonth
        spinnerYear.adapter = adapterYear
    }
}
