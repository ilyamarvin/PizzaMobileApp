package com.ilyamarvin.pizzamobileapp.ui.fragments.profile.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.ilyamarvin.pizzamobileapp.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding: FragmentRegistrationBinding
        get() = _binding!!

    private val firebaseAuth = FirebaseAuth.getInstance()
    lateinit var mVerificationId: String

    companion object {
        fun newInstance() = RegistrationFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registrationBtn.setOnClickListener {

            val name = binding.registrationName.editText?.text.toString().trim()
            val email = binding.registrationEmail.editText?.text.toString().trim()
            val dayBirthday = binding.registrationDayBirthdate.editText?.text.toString().trim()
            val monthBirthday = binding.registrationMonthBirthdate.editText?.text.toString().trim()
            val yearBirthday = binding.registrationYearBirthdate.editText?.text.toString().trim()

            if (name.isEmpty()) {
                binding.registrationName.error = "Введите имя"
                binding.registrationName.requestFocus()
                return@setOnClickListener
            }
        }
    }

}