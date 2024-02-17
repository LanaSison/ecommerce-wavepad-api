package com.example.wavepad

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class UploadPage : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var buttonChoose: Button
    private lateinit var buttonUpload: Button

    private val imageUploader = ImageUploader()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.upload_page)

        imageView = findViewById(R.id.imageView)
        buttonChoose = findViewById(R.id.buttonChoose)
        buttonUpload = findViewById(R.id.buttonUpload)

        buttonChoose.setOnClickListener {
            openImageChooser()
        }

        buttonUpload.setOnClickListener {
            val imageFile = imageView.tag as? File
            if (imageFile != null) {
                uploadImage(imageFile)
            } else {
                Toast.makeText(this, "Please select an image first", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val imageChooserLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intentData = result.data
                val selectedImageUri = intentData?.data
                if (selectedImageUri != null) {
                    imageView.setImageURI(selectedImageUri)
                    imageView.tag = File(getRealPathFromURI(selectedImageUri))
                }
            }
        }

    private fun openImageChooser() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "image/*"
        imageChooserLauncher.launch(intent)
    }

    private fun uploadImage(imageFile: File) {
        imageUploader.uploadImage(imageFile, "Description") { success, message ->
            if (success) {
                Toast.makeText(this, "Image uploaded successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Failed to upload image: $message", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getRealPathFromURI(uri: Uri): String {
        var cursor: Cursor? = null
        return try {
            val projection = arrayOf(MediaStore.Images.Media.DATA)
            cursor = contentResolver.query(uri, projection, null, null, null)
            cursor?.let {
                val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                it.moveToFirst()
                it.getString(columnIndex)
            } ?: ""
        } finally {
            cursor?.close()
        }
    }
}