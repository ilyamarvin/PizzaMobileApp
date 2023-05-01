package com.ilyamarvin.pizzamobileapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ilyamarvin.pizzamobileapp.data.model.CartItem
import com.ilyamarvin.pizzamobileapp.data.repository.MenuRepository
import com.ilyamarvin.pizzamobileapp.data.model.Product

class MenuViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    private val menuRepository = MenuRepository()
//    private val cartRepository = CartRepository(context)

    private val _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>> = _productList

//    var cart: LiveData<List<CartItem>> = cartRepository.getProductCart()

    var currentProductList = emptyList<Product>()

    private fun getProducts() {
        menuRepository.getProducts(_productList)
    }

    fun getProduct(id: Int): Product {
        return currentProductList.first {
            it.id == id
        }
    }

    fun updateCurrentProductList(productList: List<Product>) {
        currentProductList = productList
    }

//    fun addProductToCart(itemId: Int) {
//        val cartItem = CartItem(0, productId = itemId)
//        cartRepository.addToCart(cartItem)
//    }
//
//    fun removeItemById(id: Int) {
//        cartRepository.removeCartItem(id)
//    }
//
//    fun clearCart() {
//        cartRepository.clearCart()
//    }

    fun getProductsFromIds(items: List<CartItem>): List<Product> {
        val productsInCart = mutableListOf<Product>()
        items.forEach { cartItem ->
            currentProductList.firstOrNull { product ->
                product.id == cartItem.productId
            }?.let {
                productsInCart.add(it)
            }
        }
        return productsInCart
    }

    init {
        if (productList.value == null) {
            getProducts()
        }
    }

    companion object {
        private var instance: MenuViewModel? = null

        fun getInstance(application: Application): MenuViewModel {
            if (instance == null) {
                instance = MenuViewModel(application)
            }
            return instance!!
        }
    }
}
