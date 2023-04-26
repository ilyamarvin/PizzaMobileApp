package com.ilyamarvin.pizzamobileapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "address")
data class Address(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val uid: Int,
    var street: String,
    var apartment: Int,
    var floor: Int,
    var entrance: Int,
    var intercom: Int,
    var comment: String,
    var isDefault: Int
)
