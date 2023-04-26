package com.ilyamarvin.pizzamobileapp.data.local.model

import androidx.room.Embedded

data class Orders(
    @Embedded
    val orderEntity: Order?,
    @Embedded(prefix = "address")
    val addressEntity: Address?,
)