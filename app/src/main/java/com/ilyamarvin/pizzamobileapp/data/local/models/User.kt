package com.ilyamarvin.pizzamobileapp.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val userId: Int,
    var name: String,
    val phoneNumber: String,
    var email: String,
    val birthdate: String,
    var addresses: List<Address>,
    var cart: List<Cart>,
    val orders: List<Order>
)