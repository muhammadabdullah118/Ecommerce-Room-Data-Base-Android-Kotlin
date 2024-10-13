package com.example.assigmentecommerce

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class OrderAdapter(
    val context: Context,
    val listener: OrderListener
) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    private var orderList = emptyList<Order>()
    class ViewHolder( val view : View ): RecyclerView.ViewHolder(view){
        val text_name = view.findViewById<TextView>(R.id.textOrderName)
        val text_price = view.findViewById<TextView>(R.id.textOrderPrice)
        val text_id = view.findViewById<TextView>(R.id.textOrderId)
        val button_update = view.findViewById<Button>(R.id.buttonUpdateOrder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.order_rv_list, parent , false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text_id.text = orderList[position].order_id.toString()
        holder.text_name.text = orderList[position].order_name
        holder.text_price.text = orderList[position].order_price.toString()
        holder.button_update.setOnClickListener{
            listener.onUpdateClick(orderList[position])
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(order:List<Order>){
        this.orderList=order
        notifyDataSetChanged()
    }
}