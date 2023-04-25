package com.ilyamarvin.pizzamobileapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ilyamarvin.pizzamobileapp.R
import com.ilyamarvin.pizzamobileapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.orderHistoryButton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_orderHistoryFragment)
        }

        binding.deliveryAddressesButton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_deliveryAddressesFragment)
        }

        binding.logoutButton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_phoneAuthFragment)
        }



        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}