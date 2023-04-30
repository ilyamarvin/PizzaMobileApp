package com.ilyamarvin.pizzamobileapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ilyamarvin.pizzamobileapp.data.repository.MenuRepository
import com.ilyamarvin.pizzamobileapp.data.model.Product

class MenuViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    private val menuRepository = MenuRepository(context)

    private val _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>> = _productList

    var currentProductList = emptyList<Product>()

    private fun getProducts() {
        menuRepository.getProducts(_productList)
    }

    fun getProduct(id: Int): Product {
        return currentProductList.first{
            it.id == id
        }
    }

    fun updateCurrentProductList(productList: List<Product>) {
        currentProductList = productList
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
