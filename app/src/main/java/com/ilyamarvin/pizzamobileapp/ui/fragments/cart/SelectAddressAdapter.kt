package com.ilyamarvin.pizzamobileapp.ui.fragments.cart

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ilyamarvin.pizzamobileapp.data.model.Address
import com.ilyamarvin.pizzamobileapp.databinding.LayoutCartAddressCardBinding

class SelectAddressAdapter : RecyclerView.Adapter<SelectAddressAdapter.SelectAddressViewHolder>() {

    private var addressList = mutableListOf<Address>()

    lateinit var onSelectAddressClickListener: OnSelectAddressClickListener

    class SelectAddressViewHolder(val binding: LayoutCartAddressCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectAddressViewHolder {
        val binding = LayoutCartAddressCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return SelectAddressViewHolder(binding)
    }

    override fun getItemCount(): Int = addressList.size


    override fun onBindViewHolder(holder: SelectAddressViewHolder, position: Int) {

        val address = addressList[position]

        holder.binding.completeCartAddressTv.text = address.street

        holder.binding.cartAddressCard.setOnClickListener {
            onSelectAddressClickListener.onSelectAddressClick(address)
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setAddressData(addressItemList: List<Address>) {
        addressList.clear()
        addressList.addAll(addressItemList)
        notifyDataSetChanged()
    }

    interface OnSelectAddressClickListener {
        fun onSelectAddressClick(address: Address)
    }
}