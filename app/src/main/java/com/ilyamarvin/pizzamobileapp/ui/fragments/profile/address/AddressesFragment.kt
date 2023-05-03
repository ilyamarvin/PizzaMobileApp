package com.ilyamarvin.pizzamobileapp.ui.fragments.profile.address

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilyamarvin.pizzamobileapp.data.model.Address
import com.ilyamarvin.pizzamobileapp.databinding.FragmentAddressesBinding
import com.ilyamarvin.pizzamobileapp.ui.fragments.profile.ProfileViewModel

class AddressesFragment : Fragment() {

    private lateinit var binding: FragmentAddressesBinding
    private lateinit var addressAdapter: AddressAdapter

    private val profileViewModel: ProfileViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddressesBinding.inflate(inflater, container, false)
        addressAdapter = AddressAdapter()

        val layoutManager = LinearLayoutManager(requireContext())
        binding.addressesRecyclerView.layoutManager = layoutManager
        binding.addressesRecyclerView.adapter = addressAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileViewModel.address.observe(viewLifecycleOwner) {
            profileViewModel.updateCurrentAddressList(it)
            addressAdapter.setAddressData(it)

            if (profileViewModel.currentAddressList.isEmpty()) {
                binding.addressesEmpty.visibility = View.VISIBLE
            } else {
                binding.addressesEmpty.visibility = View.GONE
            }
            binding.loaderLayout.loaderFrameLayout.visibility = View.GONE
        }

        addressAdapter.onAddressClickListener = object : AddressAdapter.OnAddressClickListener {
            override fun onAddressClick(address: Address) {
                findNavController().navigate(
                    AddressesFragmentDirections.actionDeliveryAddressesFragmentToAddEditAddressFragment(
                        address.id.toString()
                    )
                )
            }

            override fun onAddressDeleteClick(address: Address) {
                profileViewModel.deleteAddress(address)
            }
        }

        binding.addressesBtnAdd.setOnClickListener {
            findNavController().navigate(
                AddressesFragmentDirections.actionDeliveryAddressesFragmentToAddEditAddressFragment(
                    ""
                )
            )
        }
    }

}