package com.ilyamarvin.pizzamobileapp.data.model

import java.util.UUID

data class Address(
    val id: String = UUID.randomUUID().toString(),
    var street: String = "",
    var apartment: Int? = 0,
    var floor: Int? = 0,
    var entrance: Int? = 0,
    var intercom: Int? = 0,
    var comment: String? = ""
)
