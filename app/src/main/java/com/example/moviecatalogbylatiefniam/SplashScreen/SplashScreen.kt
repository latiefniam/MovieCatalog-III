package com.example.moviecatalogbylatiefniam.SplashScreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.moviecatalogbylatiefniam.R
import com.example.moviecatalogbylatiefniam.home.HomeActivity

class SplashScreen : AppCompatActivity() {
    private lateinit var handler : Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        handler= Handler(Looper.getMainLooper())
        handler.postDelayed({
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        },4000)
    }
}