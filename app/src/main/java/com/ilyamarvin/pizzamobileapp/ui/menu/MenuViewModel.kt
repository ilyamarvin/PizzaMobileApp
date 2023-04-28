package com.ilyamarvin.pizzamobileapp.ui.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ilyamarvin.pizzamobileapp.model.Product

class MenuViewModel : ViewModel() {

    private val menuRepository = MenuRepository()

    private val _allProducts = MutableLiveData<List<Product>>()
    val allProducts: LiveData<List<Product>> = _allProducts

    private fun getProducts() {
        menuRepository.getProducts(_allProducts)
    }

    init {
        getProducts()
    }
}
