package com.ilyamarvin.pizzamobileapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ilyamarvin.pizzamobileapp.data.model.Address
import com.ilyamarvin.pizzamobileapp.data.model.CartItem
import com.ilyamarvin.pizzamobileapp.data.model.Order
import com.ilyamarvin.pizzamobileapp.data.model.Product
import com.ilyamarvin.pizzamobileapp.data.model.User
import java.util.UUID

class UserRepository {

    private var userLiveData: MutableLiveData<User>? = null

    private var ref = FirebaseDatabase.getInstance().getReference("users")
        .child(FirebaseAuth.getInstance().currentUser!!.uid)

    fun getUserData(): LiveData<User> {
        if (userLiveData == null) userLiveData = MutableLiveData()

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userData = snapshot.getValue(User::class.java)
                userLiveData!!.postValue(userData)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return userLiveData!!
    }

    fun updateUserData(name: String?, email: String?) {
        val map = mapOf<String, Any>("name" to name!!, "email" to email!!)
        ref.updateChildren(map)
    }

    fun getAddresses(addressLiveData: MutableLiveData<List<Address>>) {
        ref.child("address")
            .orderByChild("street")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val addresses: List<Address> =
                        snapshot.children.map { dataSnapshot ->
                            dataSnapshot.getValue(Address::class.java)!!
                        }
                    addressLiveData.postValue(addresses)
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
    }

    fun addAddress(
        street: String?,
        apartment: Int?,
        floor: Int?,
        entrance: Int?,
        intercom: Int?,
        comment: String?
    ) {

        ref.child("address").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val id = UUID.randomUUID()

                val address = Address(id.toString(), street!!, apartment, floor, entrance, intercom, comment)

                ref.child("address").child(id.toString()).setValue(address)

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun updateAddress(
        id: String,
        street: String?,
        apartment: Int?,
        floor: Int?,
        entrance: Int?,
        intercom: Int?,
        comment: String?
    ) {
        val map = mapOf<String, Any>(
            "street" to street!!,
            "apartment" to apartment!!,
            "floor" to floor!!,
            "entrance" to entrance!!,
            "intercom" to intercom!!,
            "comment" to comment!!
        )
        ref.child("address").child(id).updateChildren(map)
    }

    fun deleteAddress(id: String) {
        ref.child("address").child(id).removeValue()
    }

    fun getOrders(orderLiveData: MutableLiveData<List<Order>>) {
        ref.child("order")
            .orderByChild("id")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val orders: List<Order> =
                        snapshot.children.map { dataSnapshot ->
                            dataSnapshot.getValue(Order::class.java)!!
                        }
                    orderLiveData.postValue(orders)
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
    }

    fun addOrder(
        totalPrice: Int,
        orderDate: String,
        address: Address,
        orderProducts: List<Product>
    ) {

        ref.child("order").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    var maxId: Int = snapshot.childrenCount.toInt()
                    maxId++

                    val order = Order(maxId, totalPrice, orderDate, address, orderProducts)
                    ref.child("order").child(maxId.toString()).setValue(order)

                } else {
                    val order = Order(1, totalPrice, orderDate, address, orderProducts)
                    ref.child("order").child(1.toString()).setValue(order)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    fun getCartItems(cartProductLiveData: MutableLiveData<List<CartItem>>) {
        ref.child("cart")
            .orderByChild("product/id")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    val cartProductItems: List<CartItem> =
                        snapshot.children.map { dataSnapshot ->
                            dataSnapshot.getValue(CartItem::class.java)!!
                        }
                    cartProductLiveData.postValue(cartProductItems)
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
    }

    fun deleteCartItem(id: String) {
        ref.child("cart").child(id).removeValue()
    }

    fun clearCart() {
        ref.child("cart").removeValue()
    }

    fun addCartItem(product: Product) {

        ref.child("cart").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val id = UUID.randomUUID()

                val cartItem = CartItem(id.toString(), product)

                ref.child("cart").child(id.toString()).setValue(cartItem)

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}