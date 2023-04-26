package com.ilyamarvin.pizzamobileapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class Cart(
    @PrimaryKey(autoGenerate = true)
    val cartId: Int,
    val productId: Int,
    val quantity: Int
)
