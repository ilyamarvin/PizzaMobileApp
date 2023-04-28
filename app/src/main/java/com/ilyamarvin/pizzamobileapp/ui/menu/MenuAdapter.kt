package com.ilyamarvin.pizzamobileapp.ui.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ilyamarvin.pizzamobileapp.databinding.LayoutProductCardBinding
import com.ilyamarvin.pizzamobileapp.model.Product


class MenuAdapter(var productList: List<Product>) :
    RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

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
                .load(productList[position].linkImage)
                .skipMemoryCache(true)
                .into(binding.productImageView)
        }
    }
}