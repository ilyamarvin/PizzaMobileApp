package com.ilyamarvin.pizzamobileapp.data.model

import java.text.DateFormat

data class Order(
    val id: Int? = 0,
    val sum: Int? = 0,
    val date: String = DateFormat.getDateTimeInstance().format(System.currentTimeMillis()),
    val address: Address = Address(),
    val products: Map<String, Int> = HashMap()
)
