package com.ilyamarvin.pizzamobileapp.data.local.model

import androidx.room.Embedded

data class ProductWithCart(
    @Embedded
    val product: Product?,
    @Embedded
    val cart: Cart?
)