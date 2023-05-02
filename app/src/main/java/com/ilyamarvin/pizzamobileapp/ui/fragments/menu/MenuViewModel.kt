package com.ilyamarvin.pizzamobileapp.ui.fragments.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ilyamarvin.pizzamobileapp.data.repository.MenuRepository
import com.ilyamarvin.pizzamobileapp.data.model.Product

class MenuViewModel : ViewModel() {

    private val menuRepository = MenuRepository()

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products

    var currentProductList = emptyList<Product>()

    init {
        getProducts()
    }

    private fun getProducts() {
        menuRepository.getProducts(_products)
    }

    fun getProduct(id: Int): Product {
        return currentProductList.first {
            it.id == id
        }
    }

    fun updateCurrentProductList(productList: List<Product>) {
        currentProductList = productList
    }
}
