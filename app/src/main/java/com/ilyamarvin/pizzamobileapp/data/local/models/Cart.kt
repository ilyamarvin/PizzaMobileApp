package com.ilyamarvin.pizzamobileapp.data.local.models

import androidx.room.Entity

@Entity(tableName = "cart")
data class Cart(
    val cartId: Int,
    val products: List<Product>,
    val ownerId: Int,
    val price: Int
)
