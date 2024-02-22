package com.example.wavepad

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import de.hdodenhof.circleimageview.CircleImageView

class AccountPage : AppCompatActivity() {

    private val REQUEST_EDIT_PROFILE = 1
    private lateinit var profileImageView: CircleImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.account_page)

        profileImageView = findViewById(R.id.profileImageView)
        val editProfileButton: TextView = findViewById(R.id.editprofile)

        editProfileButton.setOnClickListener {
            val intent = Intent(this@AccountPage, EditProfilePage::class.java)
            startActivityForResult(intent, REQUEST_EDIT_PROFILE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_EDIT_PROFILE && resultCode == Activity.RESULT_OK) {
            // Update profile information based on the data returned from EditProfilePage
            val name = data?.getStringExtra("name")
            val isSeller = data?.getBooleanExtra("isSeller", false)
            val isMember = data?.getBooleanExtra("isMember", false)
            val contactInfo = data?.getStringExtra("contactInfo")
            val profileImageUriString = data?.getStringExtra("profileImageUri")

            // Update UI with the new profile information
            val nameTextView: TextView = findViewById(R.id.name)
            val sellerTextView: TextView = findViewById(R.id.sellerTextView)
            val contactInfoTextView: TextView = findViewById(R.id.contactInfoTextView)

            nameTextView.text = "Name: $name"
            sellerTextView.text = if (isSeller == true) "Seller" else "Member"
            contactInfoTextView.text = "Contact Info: $contactInfo"

            // Update profile picture
            profileImageUriString?.let { uriString ->
                val profileImageUri = Uri.parse(uriString)
                profileImageView.setImageURI(profileImageUri)
            }
        }
    }
}