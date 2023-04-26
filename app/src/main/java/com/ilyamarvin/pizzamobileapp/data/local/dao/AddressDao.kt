package com.ilyamarvin.pizzamobileapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ilyamarvin.pizzamobileapp.data.local.model.Address
import kotlinx.coroutines.flow.Flow

@Dao
interface AddressDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addAddress(address: Address)

    @Query("SELECT * FROM address")
    fun getAddresses(): Flow<List<Address>>

    @Query("SELECT * FROM address WHERE isDefault = :default")
    fun getAddress(default: Int = 1): Flow<Address>

    @Update
    fun updateAddress(address: Address)

    @Query("DELETE FROM address WHERE id = :identifier")
    fun removeAddress(identifier: Int?)

}