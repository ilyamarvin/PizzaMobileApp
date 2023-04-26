package com.ilyamarvin.pizzamobileapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val productId: Int,
    val name: String,
    val description: String,
    val price: Int,
    val linkImage: String
)
