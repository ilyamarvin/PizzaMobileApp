package com.ilyamarvin.pizzamobileapp.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.ilyamarvin.pizzamobileapp.data.local.model.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM product")
    suspend fun getProducts(): List<Product>

    @Query("SELECT * FROM product WHERE productId = :identifier")
    suspend fun getProduct(identifier: Int): Product

}