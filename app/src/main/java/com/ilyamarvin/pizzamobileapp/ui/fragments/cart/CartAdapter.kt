package com.ilyamarvin.pizzamobileapp.ui.fragments.cart

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ilyamarvin.pizzamobileapp.data.model.CartItem
import com.ilyamarvin.pizzamobileapp.databinding.LayoutCartProductCardBinding

class CartAdapter : RecyclerView.Adapter<CartAdapter.CartListViewHolder>() {

    private val cartItemsList = ArrayList<CartItem>()
    lateinit var onCartClickListener: OnCartClickListener

    inner class CartListViewHolder(val binding: LayoutCartProductCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val cartProductName = binding.cartProductTitle
        private val cartProductDesc = binding.cartProductDesc
        private val cartProductPrice = binding.cartProductPrice
        private val cartProductDeleteBtn = binding.cartProductDeleteBtn
        private val cartProductImage = binding.cartProductImageView

        fun bind(cartItem: CartItem, position: Int) {

            cartProductName.text = cartItem.product.name
            cartProductDesc.text = cartItem.product.description
            cartProductPrice.text = cartItem.product.price.toString().plus(" ₽")
            Glide.with(cartProductImage)
                .load(cartItem.product.image)
                .skipMemoryCache(true)
                .into(cartProductImage)

            cartProductDeleteBtn.setOnClickListener {
                cartItemsList.removeAt(position)
                notifyItemRemoved(position)
                onCartClickListener.removeCartItem(cartItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartListViewHolder {
        val binding = LayoutCartProductCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CartListViewHolder(binding)
    }

    override fun getItemCount(): Int = cartItemsList.size

    override fun onBindViewHolder(holder: CartListViewHolder, position: Int) {
        val cartItem = cartItemsList[position]
        holder.bind(cartItem, position)

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setCartItems(cartItems: List<CartItem>) {
        cartItemsList.clear()
        cartItemsList.addAll(cartItems)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearCart() {
        cartItemsList.clear()
        notifyDataSetChanged()
    }

    interface OnCartClickListener {
        fun removeCartItem(cartItem: CartItem)
    }
}