package com.ilyamarvin.pizzamobileapp.data.model

data class User(
    val uid: String? = "",
    var name: String? = "",
    val phoneNumber: String? = "",
    var email: String? = "",
    val birthdate: String? = ""
)