package com.ilyamarvin.pizzamobileapp.ui.fragments.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ilyamarvin.pizzamobileapp.data.model.Address
import com.ilyamarvin.pizzamobileapp.data.model.CartItem
import com.ilyamarvin.pizzamobileapp.data.model.Order
import com.ilyamarvin.pizzamobileapp.data.model.Product
import com.ilyamarvin.pizzamobileapp.data.model.User
import com.ilyamarvin.pizzamobileapp.data.repository.UserRepository

class ProfileViewModel : ViewModel() {

    private val userRepository = UserRepository()

    private val _address = MutableLiveData<List<Address>>()
    val address: LiveData<List<Address>> = _address

    var currentAddressList = emptyList<Address>()

    private val _order = MutableLiveData<List<Order>>()
    val order: LiveData<List<Order>> = _order

    var currentOrderList = mutableListOf<Order>()

    private val _cartItems = MutableLiveData<List<CartItem>>()
    val cartItems: LiveData<List<CartItem>> = _cartItems

    var currentCartItemList = mutableListOf<CartItem>()

    init {
        getAddresses()
        getCartItems()
        getOrders()
    }

    fun getUserData(): LiveData<User> {
        return userRepository.getUserData()
    }

    fun updateUserData(name: String?, email: String?) {
        userRepository.updateUserData(name!!, email!!)
    }

    private fun getAddresses() {
        userRepository.getAddresses(_address)
    }

    fun getAddress(id: Int): Address {
        return currentAddressList.first {
            it.id == id
        }
    }

    fun updateCurrentAddressList(addressList: List<Address>) {
        currentAddressList = addressList
    }

    fun updateAddress(
        id: Int?,
        street: String?,
        apartment: Int?,
        floor: Int?,
        entrance: Int?,
        intercom: Int?,
        comment: String?
    ) {
        userRepository.updateAddress(id, street, apartment, floor, entrance, intercom, comment)
    }

    fun addAddress(
        street: String?,
        apartment: Int?,
        floor: Int?,
        entrance: Int?,
        intercom: Int?,
        comment: String?
    ) {
        userRepository.addAddress(street, apartment, floor, entrance, intercom, comment)
    }

    fun deleteAddress(address: Address) {
        userRepository.deleteAddress(address.id)
    }

    private fun getOrders() {
        userRepository.getOrders(_order)
    }

    fun addOrder(
        totalPrice: Int, orderDate: String, address: Address,
        orderProducts: List<Product>
    ) {
        userRepository.addOrder(totalPrice, orderDate, address, orderProducts)
    }

    fun updateCurrentOrderItemList(orderList: List<Order>) {
        currentOrderList.clear()
        currentOrderList.addAll(orderList)
    }

    private fun getCartItems() {
        userRepository.getCartItems(_cartItems)
    }

    fun updateCurrentCartItemList(cartItemList: List<CartItem>) {
        currentCartItemList.clear()
        currentCartItemList.addAll(cartItemList)
    }

    fun deleteCartItem(cartItem: CartItem) {
        userRepository.deleteCartItem(cartItem.id)
    }

    fun clearCart() {
        userRepository.clearCart()
    }

    fun addCartItem(product: Product) {
        userRepository.addCartItem(product)
    }
}
