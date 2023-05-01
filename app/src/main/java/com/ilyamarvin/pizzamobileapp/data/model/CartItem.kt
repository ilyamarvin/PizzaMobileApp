package com.ilyamarvin.pizzamobileapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_item")
data class CartItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val productId: Int = 0,
    val name: String = "",
    val description: String = "",
    val price: Int = 0,
    val link: String? = ""
)