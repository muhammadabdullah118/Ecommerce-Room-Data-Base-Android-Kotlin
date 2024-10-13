package com.example.assigmentecommerce

interface ItemListener {
    fun onUpdateClick(item: Item)

    fun onDeleteClick(item: Item)

    fun onItemClick(item: Item)
}