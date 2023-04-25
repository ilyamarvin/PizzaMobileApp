package com.ilyamarvin.pizzamobileapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ilyamarvin.pizzamobileapp.data.local.models.Cart

@Dao
interface CartDao {

    @Query("SELECT * FROM cart")
    fun getAll(): List<Cart>

    @Insert
    fun insertAll(vararg item: Cart)
}