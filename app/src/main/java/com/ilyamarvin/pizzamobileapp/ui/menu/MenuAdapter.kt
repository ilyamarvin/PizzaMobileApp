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

    inner class MenuViewHolder(val binding: LayoutProductCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        return MenuViewHolder(
            LayoutProductCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        with(holder) {
            binding.productTitle.text = productList[position].name
            binding.productDesc.text = productList[position].description
            binding.productBtn.text = productList[position].price
            Glide.with(binding.productImageView)
                .load(productList[position].link)
                .skipMemoryCache(true)
                .into(binding.productImageView)
        }

        holder.binding.root.setOnClickListener {
            holder.binding.root.findNavController()
                .navigate(R.id.action_navigation_menu_to_productDetailsFragment)
        }
    }

    fun loadProducts(userList: List<Product>) {

        this.productList.clear()
        this.productList.addAll(userList)
        notifyDataSetChanged()

    }
}