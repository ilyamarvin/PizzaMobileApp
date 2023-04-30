package com.ilyamarvin.pizzamobileapp.ui.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ilyamarvin.pizzamobileapp.R
import com.ilyamarvin.pizzamobileapp.databinding.LayoutProductCardBinding
import com.ilyamarvin.pizzamobileapp.model.Product

class MenuAdapter : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    private val productList = mutableListOf<Product>()

    class MenuViewHolder(val binding: LayoutProductCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.productTitle.text = product.name
            binding.productDesc.text = product.description
            binding.productBtn.text = product.price
            Glide.with(binding.productImageView)
                .load(product.link)
                .skipMemoryCache(true)
                .into(binding.productImageView)
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

        val product: Product = productList[position]
        holder.bind(product)

        holder.binding.root.setOnClickListener {
            holder.binding.root.findNavController()
                .navigate(R.id.action_navigation_menu_to_productDetailsFragment)
        }
    }

    fun setProductsData(userList: List<Product>) {

        productList.clear()
        productList.addAll(userList)
        notifyDataSetChanged()

    }
}