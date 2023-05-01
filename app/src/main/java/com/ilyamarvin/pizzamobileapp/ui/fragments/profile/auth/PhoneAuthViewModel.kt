package com.ilyamarvin.pizzamobileapp.ui.fragments.profile.auth


import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.ilyamarvin.pizzamobileapp.ui.fragments.profile.FirebaseUserLiveData

class PhoneAuthViewModel : ViewModel() {

    enum class AuthenticationState {
        AUTHENTICATED, UNAUTHENTICATED, INVALID_AUTHENTICATION
    }

    val authenticationState = FirebaseUserLiveData().map { user ->
        if (user != null) {
            AuthenticationState.AUTHENTICATED
        } else {
            AuthenticationState.UNAUTHENTICATED
        }
    }
}

