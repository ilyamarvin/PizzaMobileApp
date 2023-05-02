package com.ilyamarvin.pizzamobileapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class CartItem(
    val id: Int = 0,
    val productId: Int = 0,
    val name: String = "",
    val description: String = "",
    val price: Int = 0,
    val image: String = ""
)