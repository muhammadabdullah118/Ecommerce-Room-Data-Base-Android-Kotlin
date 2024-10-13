package com.example.assigmentecommerce

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemVM(application: Application): AndroidViewModel(application) {

    val readAllData : LiveData<List<Item>>
    private val repository : ItemRepository

    init {
        val itemDao = AppDataBase.getDataBAse(application).itemDao()
        repository= ItemRepository(itemDao)
        readAllData=repository.readAllData
    }

    fun addItem(item: Item){
        viewModelScope.launch (Dispatchers.IO ){
            repository.insertItem(item)
        }
    }

    fun updateItem(item: Item){
        viewModelScope.launch (Dispatchers.IO ){
            repository.updateItem(item)
        }
    }

    fun deleteItem(item: Item){
        viewModelScope.launch (Dispatchers.IO ){
            repository.deleteItem(item)
        }
    }
}