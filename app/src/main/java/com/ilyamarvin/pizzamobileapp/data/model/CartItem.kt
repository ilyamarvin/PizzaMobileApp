package com.ilyamarvin.pizzamobileapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class CartItem(
    val id: Int = 0,
    val product: Product = Product()
)