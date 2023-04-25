package com.ilyamarvin.pizzamobileapp.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "addresses")
data class Address(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var street: String,
    var apartment: Int,
    var floor: Int,
    var entrance: Int,
    var intercom: Int,
    var comment: String
)
