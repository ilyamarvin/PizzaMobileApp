package com.ilyamarvin.pizzamobileapp.ui.profile.addresses

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ilyamarvin.pizzamobileapp.R

class DeliveryAddressesFragment : Fragment() {

    companion object {
        fun newInstance() = DeliveryAddressesFragment()
    }

    private lateinit var viewModel: DeliveryAddressesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_delivery_addresses, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DeliveryAddressesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}