package com.ilyamarvin.pizzamobileapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.ilyamarvin.pizzamobileapp.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash_screen)
        setSplashScreenTime()
    }

    private fun setSplashScreenTime() {
        Looper.myLooper()?.let {
            Handler(it).postDelayed({
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, TIME_OUT)
        }
    }

    companion object {
        private const val TIME_OUT: Long = 1500
    }
}