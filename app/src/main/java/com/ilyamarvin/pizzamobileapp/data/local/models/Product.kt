package com.ilyamarvin.pizzamobileapp.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val productId: Int,
    val name: String,
    val category: Category,
    val description: String,
    val price: Int,
    val linkImage: String
)
