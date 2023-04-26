package com.ilyamarvin.pizzamobileapp.data.local.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.DateFormat

@Entity(tableName = "orders")
data class Order(
    @PrimaryKey(autoGenerate = true)
    val orderId: Int,
    val totalPrice: Int,
    val orderDate: Long = System.currentTimeMillis(),
    val addressId: Int,
    val orderItems: Map<Product, Int>
) {
    val orderDateFormatted: String
        get() = DateFormat.getDateTimeInstance().format(orderDate)
}
