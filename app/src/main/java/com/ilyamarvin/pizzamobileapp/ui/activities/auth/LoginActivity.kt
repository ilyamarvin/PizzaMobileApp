package com.ilyamarvin.pizzamobileapp.ui.activities.auth

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.ilyamarvin.pizzamobileapp.databinding.ActivityLoginBinding
import com.ilyamarvin.pizzamobileapp.ui.activities.MainActivity
import java.util.concurrent.TimeUnit

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var auth: FirebaseAuth

    var number: String = ""

    private lateinit var storedVerificationId: String
    lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)


        binding.loaderLayout.loaderCard.visibility = View.GONE

        auth = FirebaseAuth.getInstance()

        binding.buttonCheckNumber.setOnClickListener {
            if (!validatePhoneNumber()) {
                return@setOnClickListener
            }
            login()
        }

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                startActivity(Intent(applicationContext, MainActivity::class.java))
                finish()
                Log.d(TAG, "onVerificationCompleted:$credential")
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Log.w(TAG, "onVerificationFailed", e)
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                Log.d(TAG, "onCodeSent: $verificationId")
                storedVerificationId = verificationId
                resendToken = token

                number = "+7${binding.phoneNumberEditText.text?.trim().toString().drop(1)}"

                val intent = Intent(applicationContext, ConfirmationActivity::class.java)
                intent.putExtra("storedVerificationId", storedVerificationId)
                intent.putExtra("phoneNumber", number)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun login() {
        number = binding.phoneNumberEditText.text?.trim().toString().drop(1)

        if (validatePhoneNumber()) {
            number = "+7$number"
            binding.loaderLayout.loaderCard.visibility = View.VISIBLE
            sendVerificationCode(number)
        }
    }

    private fun sendVerificationCode(number: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(number)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
        Log.d(TAG, "Auth started")
    }

    private fun validatePhoneNumber(): Boolean {
        val phoneNumber = binding.phoneNumberEditText.text.toString()
        if (TextUtils.isEmpty(phoneNumber)) {
            binding.phoneNumberEditText.error = "Поле не должно быть пустым"
            return false
        }
        if (phoneNumber.length != 11) {
            binding.phoneNumberEditText.error = "Вы ввели не полный номер телефона"
            return false
        }
        return true
    }

    companion object {
        private const val TAG = "LoginActivity"
    }
}
