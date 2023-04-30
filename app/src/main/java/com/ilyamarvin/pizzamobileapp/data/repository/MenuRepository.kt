package com.ilyamarvin.pizzamobileapp.data.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.ilyamarvin.pizzamobileapp.data.model.Product

private const val TAG = "MenuRepository"

class MenuRepository(context: Context) {

    private val database = Firebase.database
    private val productsReference = database.getReference("products/pizza")

    fun getProducts(productLiveData: MutableLiveData<List<Product>>) {
        productsReference
            .orderByChild("id")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    if (snapshot.exists()) {
                        val productItems: List<Product> =
                            snapshot.children.map { dataSnapshot ->
                                dataSnapshot.getValue(Product::class.java)!!
                            }
                        productLiveData.postValue(productItems)
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w(TAG, "loadPost:onCancelled", error.toException())
                }
            })
    }
}