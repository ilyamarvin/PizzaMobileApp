package com.ilyamarvin.pizzamobileapp.data.local.dao

import androidx.room.Insert
import androidx.room.Query
import com.ilyamarvin.pizzamobileapp.data.local.model.Order
import kotlinx.coroutines.flow.Flow

interface OrderDao {

    @Insert
    fun addItem(order: Order)

    @Query("SELECT * FROM orders")
    fun getOrders(): Flow<List<Order>>
}