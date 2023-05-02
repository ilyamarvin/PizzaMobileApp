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
import com.ilyamarvin.pizzamobileapp.data.model.User

class UserRepository {

    private var liveData: MutableLiveData<User>? = null

    private var ref = FirebaseDatabase.getInstance().getReference("users")
        .child(FirebaseAuth.getInstance().currentUser!!.uid)

    fun getUserData(): LiveData<User> {
        if (liveData == null) liveData = MutableLiveData()

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val userData = snapshot.getValue(User::class.java)
                    liveData!!.postValue(userData)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return liveData!!
    }

    fun updateUserData(name: String?, email: String?) {
        val map = mapOf<String, Any>("name" to name!!, "email" to email!!)
        ref.updateChildren(map)
    }

    fun addAddress(
        street: String?,
        apartment: Int?,
        floor: Int?,
        entrance: Int?,
        intercom: Int?,
        comment: String?
    ) {

        var maxId: Int = 1

        ref.child("address").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    maxId = snapshot.childrenCount.toInt()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        val address = Address(maxId, street!!, apartment, floor, entrance, intercom, comment)

        ref.child("address").child(maxId.toString()).setValue(address)
    }

    fun getAddresses(addressLiveData: MutableLiveData<List<Address>>) {
        ref.child("address")
            .orderByChild("id")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    if (snapshot.exists()) {
                        val addresses: List<Address> =
                            snapshot.children.map { dataSnapshot ->
                                dataSnapshot.getValue(Address::class.java)!!
                            }
                        addressLiveData.postValue(addresses)
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
    }

    fun getUserCart(cartProductLiveData: MutableLiveData<List<CartItem>>) {
        ref.child("cart")
            .orderByChild("id")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    if (snapshot.exists()) {
                        val cartProductItems: List<CartItem> =
                            snapshot.children.map { dataSnapshot ->
                                dataSnapshot.getValue(CartItem::class.java)!!
                            }
                        cartProductLiveData.postValue(cartProductItems)
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
    }
}