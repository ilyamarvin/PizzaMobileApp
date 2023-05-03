package com.ilyamarvin.pizzamobileapp.ui.fragments.profile.orders

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ilyamarvin.pizzamobileapp.data.model.Order
import com.ilyamarvin.pizzamobileapp.data.model.Product
import com.ilyamarvin.pizzamobileapp.databinding.LayoutOrderCardBinding

class OrdersAdapter : RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder>() {

    private var ordersList = emptyList<Order>()

    inner class OrdersViewHolder(val binding: LayoutOrderCardBinding) :
        RecyclerView.ViewHolder(binding.root) {


        private val orderNumber = binding.orderNumberTv
        private val orderDate = binding.orderDateTv
        private val orderAddress = binding.orderDeliveryAddress
        private val orderProducts = binding.orderPositionsList
        private val orderSum = binding.orderSumNumber

        @SuppressLint("SetTextI18n")
        fun bind(order: Order) {

            orderNumber.text = "№ ${order.id.toString()}"
            orderDate.text = order.date
            orderAddress.text = order.address.street
            orderProducts.text = order.products.toString()
            orderSum.text = "${order.sum.toString()} ₽"

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        val binding = LayoutOrderCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return OrdersViewHolder(binding)
    }

    override fun getItemCount(): Int = ordersList.size


    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {

        val order = ordersList[position]
        holder.bind(order)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setOrdersData(orderList: List<Order>) {
        ordersList = orderList
        notifyDataSetChanged()

    }
}