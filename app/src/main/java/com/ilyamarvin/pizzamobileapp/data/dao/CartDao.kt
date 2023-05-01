//package com.ilyamarvin.pizzamobileapp.data.dao
//
//import androidx.lifecycle.LiveData
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import androidx.room.Update
//import com.ilyamarvin.pizzamobileapp.data.model.CartItem
//
//@Dao
//interface CartDao {
//
//    @Insert
//    fun addToCart(cartItem: CartItem)
//
//    //    @Query("SELECT * FROM product INNER JOIN cart_item ON cart_item.productId = product.id")
//    @Query("SELECT * FROM cart_item")
//    fun getProductCart(): LiveData<List<CartItem>>
//
//    @Query("DELETE FROM cart_item WHERE productId = :id")
//    fun removeCartItem(id: Int)
//
//    @Query("DELETE FROM cart_item")
//    fun clearCart()
//
//    @Update
//    fun updateCart(cartItem: CartItem)
//}