package com.ilyamarvin.pizzamobileapp.ui.fragments.profile.address

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ilyamarvin.pizzamobileapp.data.model.Address
import com.ilyamarvin.pizzamobileapp.databinding.LayoutAddressCardBinding

class AddressAdapter : RecyclerView.Adapter<AddressAdapter.AddressViewHolder>() {

    private var addressList = emptyList<Address>()
    lateinit var onAddressClickListener: OnAddressClickListener

    inner class AddressViewHolder(val binding: LayoutAddressCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val textAddress = binding.completeAddressTv
        private val addressCard = binding.addressCard
        private val deleteAddressBtn = binding.addressDeleteBtn

        fun bind(address: Address) {

            textAddress.text = address.street

            addressCard.setOnClickListener {
                onAddressClickListener.onAddressClick(address)
            }

            deleteAddressBtn.setOnClickListener {
                onAddressClickListener.onAddressDeleteClick(address)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val binding = LayoutAddressCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return AddressViewHolder(binding)
    }

    override fun getItemCount(): Int = addressList.size


    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {

        val address = addressList[position]
        holder.bind(address)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setAddressData(addressItemList: List<Address>) {
        addressList = addressItemList
        notifyDataSetChanged()

    }

    interface OnAddressClickListener {
        fun onAddressClick(address: Address)
        fun onAddressDeleteClick(address: Address)
    }
}