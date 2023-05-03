package com.ilyamarvin.pizzamobileapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

data class CartItem(
    val id: String = UUID.randomUUID().toString(),
    val product: Product = Product()
)