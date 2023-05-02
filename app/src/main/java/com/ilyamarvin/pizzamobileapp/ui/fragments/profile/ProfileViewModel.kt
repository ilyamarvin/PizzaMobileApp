package com.ilyamarvin.pizzamobileapp.ui.fragments.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ilyamarvin.pizzamobileapp.data.model.Address
import com.ilyamarvin.pizzamobileapp.data.model.CartItem
import com.ilyamarvin.pizzamobileapp.data.model.Product
import com.ilyamarvin.pizzamobileapp.data.model.User
import com.ilyamarvin.pizzamobileapp.data.repository.MenuRepository
import com.ilyamarvin.pizzamobileapp.data.repository.UserRepository

class ProfileViewModel : ViewModel() {

    private val userRepository = UserRepository()

    private val _addresses = MutableLiveData<List<Address>>()
    val address: LiveData<List<Address>> = _addresses

    var currentAddressList = emptyList<Address>()

    init {
        getAddresses()
    }

    fun getUserData(): LiveData<User> {
        return userRepository.getUserData()
    }

    fun updateUserData(name: String?, email: String?) {
        userRepository.updateUserData(name!!, email!!)
    }

    private fun getAddresses() {
        userRepository.getAddresses(_addresses)
    }

    fun getAddress(id: Int): Address {
        return currentAddressList.first {
            it.id.toInt() == id
        }
    }

    fun updateCurrentAddressList(addressList: List<Address>) {
        currentAddressList = addressList
    }

    fun AddAddress(
        street: String?,
        apartment: Int?,
        floor: Int?,
        entrance: Int?,
        intercom: Int?,
        comment: String?
    ) {
        userRepository.addAddress(street, apartment, floor, entrance, intercom, comment)
    }


}
