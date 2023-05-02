package com.ilyamarvin.pizzamobileapp.ui.activities.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ilyamarvin.pizzamobileapp.data.model.User
import com.ilyamarvin.pizzamobileapp.databinding.ActivityRegistrationBinding
import com.ilyamarvin.pizzamobileapp.ui.activities.MainActivity


class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding

    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    val databaseReference: DatabaseReference = firebaseDatabase.reference.child("users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistrationBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val phoneNumber = intent.getStringExtra("phoneNumber")

        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()

        val name = binding.registrationName
        val email = binding.registrationEmail
        val dayBirthday = binding.registrationDayBirthdate
        val monthBirthday = binding.registrationMonthBirthdate
        val yearBirthday = binding.registrationYearBirthdate

        binding.registrationBtn.setOnClickListener {

            val inputName = name.editText?.text.toString().trim()
            val inputEmail = email.editText?.text.toString().trim()
            val inputDayBirthday = dayBirthday.editText?.text.toString().trim()
            val inputMonthBirthday = monthBirthday.editText?.text.toString().trim()
            val inputYearBirthday = yearBirthday.editText?.text.toString().trim()

            if (inputName.isEmpty()) {
                name.error = "Это поле не может быть пустым"
                name.requestFocus()
                return@setOnClickListener
            } else name.error = null

            if (inputEmail.isEmpty()) {
                email.error = "Это поле не может быть пустым"
                binding.registrationName.requestFocus()
                return@setOnClickListener
            } else if (!inputEmail.matches(emailPattern)) {
                email.error = "Неправильная электронная почта"
                binding.registrationName.requestFocus()
                return@setOnClickListener
            } else {
                email.error = null
            }
            if (inputDayBirthday.isEmpty()) {
                dayBirthday.error = "Это поле не может быть пустым"
                dayBirthday.requestFocus()
                return@setOnClickListener
            } else dayBirthday.error = null

            if (inputMonthBirthday.isEmpty()) {
                monthBirthday.error = "Это поле не может быть пустым"
                monthBirthday.requestFocus()
                return@setOnClickListener
            } else monthBirthday.error = null

            if (inputYearBirthday.isEmpty()) {
                yearBirthday.error = "Это поле не может быть пустым"
                yearBirthday.requestFocus()
                return@setOnClickListener
            } else yearBirthday.error = null

            val birthdate = "$inputDayBirthday.$inputMonthBirthday.$inputYearBirthday"

            val user =
                User(firebaseAuth.currentUser!!.uid, inputName, phoneNumber, inputEmail, birthdate)

            databaseReference.child(firebaseAuth.currentUser!!.uid).setValue(user)
                .addOnCompleteListener {
                    Toast.makeText(this, "Вы успешно зарегистрировались!", Toast.LENGTH_LONG).show()
                }.addOnFailureListener {
                    Toast.makeText(this, "Произошла ошибка", Toast.LENGTH_LONG).show()
                }

            val intent = Intent(this@RegistrationActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
