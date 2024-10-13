package com.example.assigmentecommerce

import androidx.lifecycle.LiveData

class OrderRepository(private var orderDao : OrderDao) {

    val readAllData : LiveData<List<Order>> = orderDao.readAllData()

    suspend fun insertOrder(order: Order){
        orderDao.insertOrder(order)
    }

    suspend fun updateOrder(order: Order){
        orderDao.updateOrder(order)
    }

    suspend fun deleteAllHistory(){
        orderDao.deleteAllOrderHistory()
    }
}