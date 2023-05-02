package com.ilyamarvin.pizzamobileapp.ui.activities.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ilyamarvin.pizzamobileapp.databinding.ActivityConfirmationBinding
import com.ilyamarvin.pizzamobileapp.ui.activities.MainActivity

class ConfirmationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfirmationBinding

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityConfirmationBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.loaderLayout.loaderCard.visibility = View.GONE

        auth = FirebaseAuth.getInstance()

        val storedVerificationId = intent.getStringExtra("storedVerificationId")

        binding.confirmationBtn.setOnClickListener {
            val otp = binding.confirmationCodeEditText.text.toString()

            if (validateCode()) {
                val credential: PhoneAuthCredential =
                    PhoneAuthProvider.getCredential(storedVerificationId.toString(), otp)
                binding.loaderLayout.loaderCard.visibility = View.VISIBLE
                signInWithPhoneAuthCredential(credential)
            }
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        val phoneNumber = intent.getStringExtra("phoneNumber")
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val ref: DatabaseReference = FirebaseDatabase.getInstance().reference
                    ref.child("users").child(auth.currentUser!!.uid).addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            if (snapshot.exists()) {
                                val intent = Intent(this@ConfirmationActivity, MainActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                            else {
                                val intent = Intent(this@ConfirmationActivity, RegistrationActivity::class.java)
                                intent.putExtra("phoneNumber", phoneNumber)
                                startActivity(intent)
                                finish()
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            TODO("Not yet implemented")
                        }
                    })
                } else {
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(this, "Вы ввели неверный код", Toast.LENGTH_SHORT).show()
                        binding.confirmationCodeEditText.text?.clear()
                    }
                }
            }
    }

    private fun validateCode(): Boolean {
        val code = binding.confirmationCodeEditText.text.toString()
        if (TextUtils.isEmpty(code)) {
            binding.confirmationCodeEditText.error = "Поле не должно быть пустым"
            return false
        }
        if (code.length != 6) {
            binding.confirmationCodeEditText.error = "Код состоит из 6 значений"
            return false
        }
        return true
    }
}