package com.example.assigmentecommerce

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ItemListAdapter (
    val context: Context,
    val listener: ItemListener
):RecyclerView.Adapter<ItemListAdapter.ViewHolder>(){

    private var itemList = emptyList<Item>()
    class ViewHolder(val view : View) : RecyclerView.ViewHolder(view){
        val itemName = view.findViewById<TextView>(R.id.textItemName)
        val itemPrice = view.findViewById<TextView>(R.id.textItemPrice)
        val buttonUpdate = view.findViewById<TextView>(R.id.buttonUpdateItem)
        val buttonDelete = view.findViewById<TextView>(R.id.buttonDeleteItem)
        val cardView = view.findViewById<CardView>(R.id.itemCardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_rv_list,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemName.text = itemList[position].name
        holder.itemPrice.text =itemList[position].price.toString()
        holder.cardView.setOnClickListener{
            listener.onItemClick(itemList[position])
        }
        holder.buttonDelete.setOnClickListener{
            listener.onDeleteClick(itemList[position])}
        holder.buttonUpdate.setOnClickListener{
            listener.onUpdateClick(itemList[position])
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(item:List<Item>){
        this.itemList=item
        notifyDataSetChanged()
    }
}