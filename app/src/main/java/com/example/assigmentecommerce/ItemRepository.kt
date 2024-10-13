package com.example.assigmentecommerce

import androidx.lifecycle.LiveData

class ItemRepository(private var itemDao: ItemDao) {

    val readAllData : LiveData<List<Item>> = itemDao.readAllData()

    suspend fun insertItem(item: Item){
        itemDao.insertItem(item)
    }

    suspend fun updateItem(item: Item){
        itemDao.updateItem(item)
    }

    suspend fun deleteItem(item: Item){
        itemDao.deleteItem(item)
    }
}