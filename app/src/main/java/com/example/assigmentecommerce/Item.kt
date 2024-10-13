package com.example.assigmentecommerce

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "item_data")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id : Int ,
    val name : String ,
    val price : Int ,
    val description : String
):Parcelable
