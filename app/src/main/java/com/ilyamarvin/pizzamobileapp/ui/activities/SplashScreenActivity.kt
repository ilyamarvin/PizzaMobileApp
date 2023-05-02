package com.ilyamarvin.pizzamobileapp.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.firebase.auth.FirebaseAuth
import com.ilyamarvin.pizzamobileapp.R
import com.ilyamarvin.pizzamobileapp.ui.activities.auth.LoginActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var userAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userAuth = FirebaseAuth.getInstance()

        setContentView(R.layout.activity_splash_screen)
        setSplashScreenTime()
    }

    private fun setSplashScreenTime() {
        Looper.myLooper()?.let {
            Handler(it).postDelayed({
                if (userAuth.currentUser != null) {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
                else startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }, TIME_OUT)
        }
    }

    companion object {
        private const val TIME_OUT: Long = 1500
    }
}