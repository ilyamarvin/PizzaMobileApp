package com.ilyamarvin.pizzamobileapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ilyamarvin.pizzamobileapp.data.model.CartItem
import com.ilyamarvin.pizzamobileapp.databinding.LayoutCartProductCardBinding
import java.util.LinkedList

class CartListAdapter : RecyclerView.Adapter<CartListAdapter.CartListViewHolder>() {

    private val cartProductList = mutableListOf<CartItem>()
    lateinit var onCartClickListener: OnCartClickListener

    inner class CartListViewHolder(val binding: LayoutCartProductCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val cartProductName = binding.cartProductTitle
        private val cartProductDesc = binding.cartProductDesc
        private val cartProductPrice = binding.cartProductPrice
        private val cartProductDeleteBtn = binding.cartProductDeleteBtn
        private val cartProductImage = binding.cartProductImageView

        fun bind(cartItem: CartItem) {

            cartProductName.text = cartItem.name
            cartProductDesc.text = cartItem.description
            cartProductPrice.text = cartItem.price.toString().plus(" ₽")
            Glide.with(cartProductImage)
                .load(cartItem.link)
                .skipMemoryCache(true)
                .into(cartProductImage)

            cartProductDeleteBtn.setOnClickListener {
                onCartClickListener.removeItemFromCart(cartItem.productId)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartListViewHolder {
        val binding = LayoutCartProductCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CartListViewHolder(binding)
    }

    override fun getItemCount(): Int = cartProductList.size

    override fun onBindViewHolder(holder: CartListViewHolder, position: Int) {
        holder.bind(cartProductList[position])
    }

    fun setCartItems(cartItems: List<CartItem>) {
        cartProductList.clear()
        cartProductList.addAll(cartItems)

        notifyDataSetChanged()
    }

    fun addCartItem(cartItem: CartItem) {
        cartProductList.add(0, cartItem)

        notifyItemInserted(0)
    }

    fun removeCartItem(productId: Int) {
        cartProductList.removeAt(productId)

        notifyItemRemoved(productId)
    }

    interface OnCartClickListener {
        fun removeItemFromCart(productId: Int)
//        fun changeQuantity(cartItem: CartItem, quantity: Int)
    }
}