package com.ilyamarvin.pizzamobileapp.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class Order(
    @PrimaryKey(autoGenerate = true)
    val orderId: Int,
    val orderDate: String,
    val addressDelivery: Address,
    val orderItems: Map<Product, Int>
)
