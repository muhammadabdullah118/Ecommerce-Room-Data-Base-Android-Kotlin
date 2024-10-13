package com.example.assigmentecommerce

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "order_data")
data class Order(
    @PrimaryKey(autoGenerate = true)
    val order_id : Int,
    val order_name : String,
    val order_price : Int,
    val order_quantity : Int
):Parcelable
