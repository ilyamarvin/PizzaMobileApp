package com.ilyamarvin.pizzamobileapp.ui.auth

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.ilyamarvin.pizzamobileapp.databinding.FragmentPhoneAuthBinding
import com.ilyamarvin.pizzamobileapp.viewmodel.PhoneAuthViewModel

class PhoneAuthFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    private var _binding: FragmentPhoneAuthBinding? = null
    private val binding: FragmentPhoneAuthBinding
        get() = _binding!!

    private lateinit var phoneAuthViewModel: PhoneAuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        phoneAuthViewModel = ViewModelProvider(this)[PhoneAuthViewModel::class.java]

        _binding = FragmentPhoneAuthBinding.inflate(inflater, container, false)
        binding.editTextPhoneAuth.requestFocus()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonProfileAuth.setOnClickListener {
            if (!validatePhoneNumber()) {
                return@setOnClickListener
            }
            getUserPhone()

        }
    }

    private fun getUserPhone() {
        phoneAuthViewModel.phone.value = binding.editTextPhoneAuth.editText?.text.toString().trim()
    }




//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putBoolean(PHONE_AUTH_VERIFY, verificationInProgress)
//    }
//
//    override fun onViewStateRestored(savedInstanceState: Bundle?) {
//        super.onViewStateRestored(savedInstanceState)
//        savedInstanceState?.let { savedState ->
//            verificationInProgress = savedState.getBoolean(PHONE_AUTH_VERIFY)
//        }
//    }

    private fun validatePhoneNumber(): Boolean {
        val phoneNumber = binding.editTextPhoneAuth.editText?.text.toString()
        if (TextUtils.isEmpty(phoneNumber)) {
            binding.editTextPhoneAuth.error = "Вы не ввели номер телефона"
            return false
        }

        return true
    }
}