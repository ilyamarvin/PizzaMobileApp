package com.ilyamarvin.pizzamobileapp.data.model

data class Order(
    val id: Int? = 0,
    val total: Int? = 0,
    val date: String = "",
    val address: Address = Address(),
    val products: List<Product> = emptyList()
)
