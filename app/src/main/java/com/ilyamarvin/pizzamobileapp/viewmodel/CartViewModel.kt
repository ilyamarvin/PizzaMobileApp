//package com.ilyamarvin.pizzamobileapp.viewmodel
//
//import android.app.Application
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import com.ilyamarvin.pizzamobileapp.data.model.Address
//import com.ilyamarvin.pizzamobileapp.data.model.CartItem
//import com.ilyamarvin.pizzamobileapp.data.model.Order
//import com.ilyamarvin.pizzamobileapp.data.repository.CartRepository
//
//class CartViewModel(private val cartRepository: CartRepository) : ViewModel() {
//
//    private val _userAddresses = MutableLiveData<List<Address>>()
//    val userAddresses: LiveData<List<Address>> get() = _userAddresses
//
//    private val _cartItems = MutableLiveData<List<CartItem>>()
//    val cartItems: LiveData<List<CartItem>> get() = _cartItems
//
//    private val _selectedAddress = MutableLiveData<Int>()
//    private val newOrderData = MutableLiveData<Order>()
//
//    fun getCartItems() {
//        val cartItems = cartRepository.getProductCart()
//        _cartItems.value = cartItems
//    }
//
//    fun clearCart() {
//        cartRepository.clearCart()
//    }
//
//
//
//    fun addProductToCart(productId: Int) {
//        val cartItem = Cart(0, productId)
//        cartRepository.insertCartItem(cartItem)
//    }
//
////    fun getUserAddresses() {
////        viewModelScope.launch {
////            val res = authRepository.getAddressesByUserId(currentUser!!)
////            if (res != null) {
////                _userAddresses.value = res.data ?: emptyList()
////            } else {
////                _userAddresses.value = emptyList()
////            }
////        }
////    }
//
//    fun getItemsPriceTotal(): Int {
//        var totalPrice = 0
//        _priceList.value?.forEach { (itemId, price) ->
//            totalPrice += price * (_cartItems.value?.find { it.productId == itemId }?.quantity ?: 1)
//        }
//        return totalPrice
//    }
//
//    fun getItemsCount(): Int {
//        var totalCount = 0
//        _cartItems.value?.forEach {
//            totalCount += it.quantity
//        }
//        return totalCount
//    }
//
//    fun setSelectedAddress(addressId: Int) {
//        _selectedAddress.value = addressId
//    }
//
//    companion object {
//        private var instance: CartViewModel? = null
//
//        fun getInstance(application: Application): CartViewModel {
//            if (instance == null) {
//                instance = CartViewModel(application)
//            }
//            return instance!!
//        }
//    }
//}