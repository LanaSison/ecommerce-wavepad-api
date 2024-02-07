package com.example.wavepad

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SplashScreen : AppCompatActivity() {

    private lateinit var logo: ImageView
    private lateinit var welcomeText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        logo = findViewById(R.id.logosplash)
        welcomeText = findViewById(R.id.welcometext)

        logo.alpha = 0f
        logo.animate().setDuration(2500).alpha(1f).withEndAction {
            animateTextAppearance()
        }
    }

    private fun animateTextAppearance() {
        val text = "Welcome To WavePad"
        val delay = 100L

        for (i in text.indices) {
            Handler().postDelayed({
                welcomeText.text = text.substring(0, i + 1)
            }, i * delay)
        }

        Handler().postDelayed({
            animateTextDisappearance(text.length)
        }, text.length * delay + 1500)
    }

    private fun animateTextDisappearance(length: Int) {
        val delay = 100L

        for (i in length downTo 0) {
            Handler().postDelayed({
                welcomeText.text = welcomeText.text.toString().substring(0, i)
            }, (length - i) * delay)
        }
        Handler().postDelayed({
            val intent = Intent(this@SplashScreen, SignUpLogInPage::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }, length * delay)
    }
}
