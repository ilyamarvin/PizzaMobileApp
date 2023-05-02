package com.ilyamarvin.pizzamobileapp.ui.fragments.profile

import android.annotation.SuppressLint
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

    private val profileViewModel: ProfileViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val regex = """(\d)(\d{3})(\d{3})(\d{2})(\d{2})""".toRegex()

        profileViewModel.getUserData().observe(viewLifecycleOwner) {

            val number = it.phoneNumber?.let { num -> regex.replace(num, "$1 ($2) $3-$4-$5") }

            binding.profileHello.text = "Здравствуйте, ${it.name}!"
            binding.profileNumber.text = number
        }

        binding.editProfileButton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_editProfileFragment)
        }

        binding.orderHistoryButton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_orderHistoryFragment)
        }

        binding.deliveryAddressesButton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_deliveryAddressesFragment)
        }

    }
}