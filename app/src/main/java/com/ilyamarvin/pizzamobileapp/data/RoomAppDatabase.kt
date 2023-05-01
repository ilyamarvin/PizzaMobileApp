//package com.ilyamarvin.pizzamobileapp.data
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.ilyamarvin.pizzamobileapp.data.dao.CartDao
//import com.ilyamarvin.pizzamobileapp.data.model.CartItem
//
//@Database(entities = [CartItem::class], version = 1, exportSchema = false)
//abstract class RoomAppDatabase: RoomDatabase() {
//
//    abstract fun cartDao(): CartDao
//
//    companion object {
//        private var INSTANCE: RoomAppDatabase? = null
//
//        fun getInstance(context: Context): RoomAppDatabase {
//            var instance = INSTANCE
//            if (instance == null) {
//                instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    RoomAppDatabase::class.java,
//                    "room_app_database"
//                )
//                    .allowMainThreadQueries()
//                    .build()
//                INSTANCE = instance
//            }
//            return instance
//        }
//    }
//}