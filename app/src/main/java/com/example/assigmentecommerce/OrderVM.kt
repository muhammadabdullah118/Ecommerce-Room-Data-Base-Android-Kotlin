package com.example.assigmentecommerce

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderVM(application: Application): AndroidViewModel(application) {

    val readAllData : LiveData<List<Order>>
    private val repository : OrderRepository

    init {
        val orderDao = AppDataBase.getDataBAse(application).orderDao()
        repository=OrderRepository(orderDao)
        readAllData=repository.readAllData
    }

    fun addOrder(order: Order){
        viewModelScope.launch (Dispatchers.IO ){
            repository.insertOrder(order)
        }
    }

    fun updateOrder(order: Order){
        viewModelScope.launch (Dispatchers.IO ){
            repository.updateOrder(order)
        }
    }

    fun deleteAllOrderHistroy(){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteAllHistory()
        }
    }

}