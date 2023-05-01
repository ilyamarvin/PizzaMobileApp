package com.ilyamarvin.pizzamobileapp.data.model

import java.text.DateFormat

data class Order(
    val orderId: Int? = 0,
    val totalPrice: Int? = 0,
    val orderDate: Long = System.currentTimeMillis(),
    val addressId: Int? = 0,
    val orderItems: Map<Product, Int>
) {
    val orderDateFormatted: String
        get() = DateFormat.getDateTimeInstance().format(orderDate)
}
