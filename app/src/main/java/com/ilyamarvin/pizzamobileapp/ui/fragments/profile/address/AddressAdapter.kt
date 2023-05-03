package com.ilyamarvin.pizzamobileapp.ui.fragments.profile.address

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ilyamarvin.pizzamobileapp.data.model.Address
import com.ilyamarvin.pizzamobileapp.databinding.LayoutAddressCardBinding

class AddressAdapter : RecyclerView.Adapter<AddressAdapter.AddressViewHolder>() {

    private var addressList = mutableListOf<Address>()

    lateinit var onAddressClickListener: OnAddressClickListener

    class AddressViewHolder(val binding: LayoutAddressCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val binding = LayoutAddressCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return AddressViewHolder(binding)
    }

    override fun getItemCount(): Int = addressList.size


    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {

        val address = addressList[position]

        holder.binding.completeAddressTv.text = address.street

        holder.binding.addressCard.setOnClickListener {
            onAddressClickListener.onAddressClick(address)
        }

        holder.binding.addressDeleteBtn.setOnClickListener {
            addressList.removeAt(position)
            notifyItemRemoved(position)
            onAddressClickListener.onAddressDeleteClick(address)
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setAddressData(addressItemList: List<Address>) {
        addressList.clear()
        addressList.addAll(addressItemList)
        notifyDataSetChanged()
    }

    interface OnAddressClickListener {
        fun onAddressClick(address: Address)
        fun onAddressDeleteClick(address: Address)
    }
}