package com.ilyamarvin.pizzamobileapp.ui.fragments.profile.auth

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ilyamarvin.pizzamobileapp.R
import com.ilyamarvin.pizzamobileapp.databinding.FragmentPhoneAuthBinding
import java.util.concurrent.TimeUnit

class PhoneAuthFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    private var _binding: FragmentPhoneAuthBinding? = null
    private val binding: FragmentPhoneAuthBinding
        get() = _binding!!

    private var verificationInProgress = false
    private var storedVerificationId: String? = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhoneAuthBinding.inflate(inflater, container, false)

        binding.confirmationTitle.visibility = View.GONE
        binding.confirmationDesc.visibility = View.GONE
        binding.confirmationCodeTextInput.visibility = View.GONE
        binding.buttonCheckCode.visibility = View.GONE
        binding.loaderLayout.loaderCard.visibility = View.GONE

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        savedInstanceState?.let { onViewStateRestored(it) }

        binding.buttonCheckNumber.setOnClickListener {
            if (!validatePhoneNumber()) {
                return@setOnClickListener
            }

            binding.confirmationTitle.visibility = View.VISIBLE
            binding.confirmationDesc.visibility = View.VISIBLE
            binding.confirmationCodeTextInput.visibility = View.VISIBLE
            binding.buttonCheckCode.visibility = View.VISIBLE

            binding.imageviewProfile.visibility = View.GONE
            binding.textAuthProfileDesc.visibility = View.GONE
            binding.textPhoneAuth.visibility = View.GONE
            binding.phoneNumberTextInput.visibility = View.GONE
            binding.buttonCheckNumber.visibility = View.GONE

            startPhoneNumberVerification("+7" + (binding.phoneNumberEditText.text.toString()).drop(1))
        }
        binding.buttonCheckCode.setOnClickListener {
            val code = binding.confirmationCodeEditText.text.toString()
            if (TextUtils.isEmpty(code)) {
                binding.confirmationCodeEditText.error = "Поле не может быть пустым"
                return@setOnClickListener
            }
            verifyPhoneNumberWithCode(storedVerificationId, code)
        }

        auth = Firebase.auth

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                Log.d(TAG, "onVerificationCompleted:$credential")
                verificationInProgress = false

                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Log.w(TAG, "onVerificationFailed", e)
                verificationInProgress = false

                if (e is FirebaseAuthInvalidCredentialsException) {
                    binding.phoneNumberEditText.error = "Неправильный номер телефона"
                } else if (e is FirebaseTooManyRequestsException) {
                    Snackbar.make(
                        view, "Слишком много запросов",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                Log.d(TAG, "onCodeSent:$verificationId")

                storedVerificationId = verificationId
                resendToken = token
            }
        }
    }

    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser

        if (verificationInProgress && validatePhoneNumber()) {
            startPhoneNumberVerification(binding.phoneNumberEditText.text.toString())
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_VERIFY_IN_PROGRESS, verificationInProgress)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.let { savedState ->
            verificationInProgress = savedState.getBoolean(KEY_VERIFY_IN_PROGRESS)
        }
    }

    private fun startPhoneNumberVerification(phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
        verificationInProgress = true
    }

    private fun verifyPhoneNumberWithCode(verificationId: String?, code: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)

        binding.loaderLayout.loaderCard.visibility = View.VISIBLE
        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {

                    binding.loaderLayout.loaderCard.visibility = View.GONE
                    Log.d(TAG, "signInWithCredential:success")

                    val user = task.result?.user
                    findNavController().navigate(R.id.action_phoneAuthFragment_to_navigation_profile)
                } else {

                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {

                        binding.confirmationCodeEditText.error = "Неверный код"
                    }

                }
            }
    }

    private fun validatePhoneNumber(): Boolean {
        val phoneNumber = binding.phoneNumberEditText.text.toString()
        if (TextUtils.isEmpty(phoneNumber)) {
            binding.phoneNumberEditText.error = "Вы не ввели номер телефона"
            return false
        }

        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "PhoneAuthFragment"
        private const val KEY_VERIFY_IN_PROGRESS = "key_verify_in_progress"
    }
}