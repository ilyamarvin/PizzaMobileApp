package com.ilyamarvin.pizzamobileapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ilyamarvin.pizzamobileapp.databinding.LayoutProductCardBinding
import com.ilyamarvin.pizzamobileapp.data.model.Product

class MenuAdapter : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    private val productList = mutableListOf<Product>()
    lateinit var onClickListener: OnClickListener

    inner class MenuViewHolder(val binding: LayoutProductCardBinding) :
        RecyclerView.ViewHolder(binding.root) {


        private val productName = binding.productTitle
        private val productDesc = binding.productDesc
        private val productPriceCartBtn = binding.productBtn
        private val productCard = binding.productCard
        private val productImage = binding.productImageView

        fun bind(product: Product) {

            productName.text = product.name
            productDesc.text = product.description
            productPriceCartBtn.text = product.price
            Glide.with(productImage)
                .load(product.link)
                .skipMemoryCache(true)
                .into(productImage)

            productCard.setOnClickListener {
                onClickListener.onClick(product)
            }

            productPriceCartBtn.setOnClickListener {
                onClickListener.onAddToCartClick(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = LayoutProductCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MenuViewHolder(binding)
    }

    override fun getItemCount(): Int = productList.size


    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {

        val product = productList[position]
        holder.bind(product)
    }

    fun setProductsData(userList: List<Product>) {

        productList.clear()
        productList.addAll(userList)
        notifyDataSetChanged()

    }

    interface OnClickListener {
        fun onClick(product: Product)
        fun onAddToCartClick(product: Product)
    }
}