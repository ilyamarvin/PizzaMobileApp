//package com.ilyamarvin.pizzamobileapp.data.repository
//
//import android.content.Context
//import androidx.lifecycle.LiveData
//import com.ilyamarvin.pizzamobileapp.data.RoomAppDatabase
//import com.ilyamarvin.pizzamobileapp.data.dao.CartDao
//import com.ilyamarvin.pizzamobileapp.data.model.CartItem
//
//
//class CartRepository(context: Context) {
//
//    private val cartDao = RoomAppDatabase.getInstance(context).cartDao()
//
//    fun getProductCart(): LiveData<List<CartItem>> = cartDao.getProductCart()
//
//    fun addToCart(cartItem: CartItem) {
//        cartDao.addToCart(cartItem)
//    }
//
//    fun removeCartItem(id: Int) {
//        cartDao.removeCartItem(id)
//    }
//
//    fun clearCart() {
//        cartDao.clearCart()
//    }
//
//    fun updateCart(cartItem: CartItem) {
//        cartDao.updateCart(cartItem)
//    }
//
//}