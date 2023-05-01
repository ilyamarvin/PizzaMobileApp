package com.ilyamarvin.pizzamobileapp.data.model

data class Address(
    val id: Int = 0,
    val uid: Int = 0,
    var street: String = "",
    var apartment: Int? = 0,
    var floor: Int? = 0,
    var entrance: Int? = 0,
    var intercom: Int? = 0,
    var comment: String? = ""
)
