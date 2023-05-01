//package com.ilyamarvin.pizzamobileapp.data.repository
//
//import android.content.Context
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import com.ilyamarvin.pizzamobileapp.data.model.CartItem
//import com.ilyamarvin.pizzamobileapp.data.model.Product
//
//
//class CartRepository(context: Context) {
//
//    private val mutableCart = MutableLiveData<List<CartItem>>()
//    private val mutableTotalPrice = MutableLiveData<Int>()
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