package com.ilyamarvin.pizzamobileapp.ui.fragments.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ilyamarvin.pizzamobileapp.R
import com.ilyamarvin.pizzamobileapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.orderHistoryButton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_orderHistoryFragment)
        }

        binding.deliveryAddressesButton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_deliveryAddressesFragment)
        }

    }
}