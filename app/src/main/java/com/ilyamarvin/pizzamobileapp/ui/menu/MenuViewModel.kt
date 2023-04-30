package com.ilyamarvin.pizzamobileapp.ui.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ilyamarvin.pizzamobileapp.data.repository.MenuRepository
import com.ilyamarvin.pizzamobileapp.model.Product

class MenuViewModel : ViewModel() {

    private val menuRepository = MenuRepository()

    private val _allProducts = MutableLiveData<List<Product>>()
    val allProducts: LiveData<List<Product>> = _allProducts

    fun getProducts() {
        menuRepository.getProducts(_allProducts)
    }

    init {
        if (allProducts.value == null) {
            getProducts()
        }
    }
}
