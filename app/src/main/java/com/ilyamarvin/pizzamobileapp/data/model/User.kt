package com.ilyamarvin.pizzamobileapp.data.model

data class User(
    val uid: Int? = 0,
    var name: String? = "",
    val phoneNumber: String? = "",
    var email: String? = "",
    val birthdate: String? = ""
)