package com.ilyamarvin.pizzamobileapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ilyamarvin.pizzamobileapp.data.local.model.Cart
import com.ilyamarvin.pizzamobileapp.data.local.model.ProductWithCart
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Insert
    suspend fun addToCart(cart: Cart)

    @Query("SELECT * FROM product INNER JOIN cart ON cart.productId = product.productId")
    fun getProductCart(): Flow<List<ProductWithCart>>

    @Query("DELETE FROM cart WHERE cartId = :identifier")
    fun removeCart(identifier: Int?)

    @Update
    fun updateCart(cartEntity: Cart)

    @Query("SELECT SUM(quantity) FROM cart")
    fun countProductsCart(): Int

}