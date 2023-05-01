package com.ilyamarvin.pizzamobileapp.ui.fragments.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ilyamarvin.pizzamobileapp.R
import com.ilyamarvin.pizzamobileapp.databinding.FragmentAddressesBinding

class AddressesFragment : Fragment() {

    private var _binding: FragmentAddressesBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = AddressesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddressesBinding.inflate(inflater, container, false)

        binding.addressesBtnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_deliveryAddressesFragment_to_addEditAddressFragment)
        }

        return binding.root
    }

}