package com.ilyamarvin.pizzamobileapp.ui.fragments.shop.cart

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ilyamarvin.pizzamobileapp.data.model.CartItem
import com.ilyamarvin.pizzamobileapp.databinding.LayoutCartProductCardBinding

class CartAdapter : RecyclerView.Adapter<CartAdapter.CartListViewHolder>() {

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

            cartProductName.text = cartItem.product.name
            cartProductDesc.text = cartItem.product.description
            cartProductPrice.text = cartItem.product.price.toString().plus(" ₽")
            Glide.with(cartProductImage)
                .load(cartItem.product.image)
                .skipMemoryCache(true)
                .into(cartProductImage)

            cartProductDeleteBtn.setOnClickListener {
                onCartClickListener.removeItemFromCart(cartItem.product.id)
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

    @SuppressLint("NotifyDataSetChanged")
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