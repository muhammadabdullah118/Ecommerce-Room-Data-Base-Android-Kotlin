package com.example.assigmentecommerce

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Item::class , Order :: class], version = 1, exportSchema = false)
abstract class AppDataBase(): RoomDatabase() {

    abstract fun itemDao() : ItemDao
    abstract fun orderDao() : OrderDao

    companion object{
        @Volatile
        private var INSTANCE : AppDataBase ?= null

        fun getDataBAse(context: Context):AppDataBase{
            return INSTANCE ?: synchronized(this){
                Room.databaseBuilder(
                    context,AppDataBase::class.java,"app_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}