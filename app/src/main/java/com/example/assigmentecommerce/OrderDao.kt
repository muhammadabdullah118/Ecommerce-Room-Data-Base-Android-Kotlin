package com.example.assigmentecommerce

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface OrderDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrder(order: Order)

    @Query("SELECT * FROM order_data ORDER BY order_id ASC")
    fun readAllData(): LiveData<List<Order>>

    @Update
    suspend fun updateOrder(order: Order)

    @Query("DELETE FROM order_data")
    suspend fun deleteAllOrderHistory()

}