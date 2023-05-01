package com.ilyamarvin.pizzamobileapp.ui.fragments.auth


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PhoneAuthViewModel() : ViewModel() {

    val phone = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val dayBirthday = MutableLiveData<String>()
    val monthBirthday = MutableLiveData<String>()
    val yearBirthday = MutableLiveData<String>()
}

