package com.ilyamarvin.pizzamobileapp.ui.fragments.profile.orders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilyamarvin.pizzamobileapp.R
import com.ilyamarvin.pizzamobileapp.databinding.FragmentAddressesBinding
import com.ilyamarvin.pizzamobileapp.databinding.FragmentOrderHistoryBinding
import com.ilyamarvin.pizzamobileapp.databinding.LayoutOrderCardBinding
import com.ilyamarvin.pizzamobileapp.ui.fragments.profile.ProfileViewModel
import com.ilyamarvin.pizzamobileapp.ui.fragments.profile.address.AddressAdapter

class OrdersFragment : Fragment() {

    private lateinit var binding: FragmentOrderHistoryBinding
    private lateinit var ordersAdapter: OrdersAdapter

    private val profileViewModel: ProfileViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderHistoryBinding.inflate(inflater, container, false)
        ordersAdapter = OrdersAdapter()

        val layoutManager = LinearLayoutManager(requireContext())
        binding.ordersRecyclerView.layoutManager = layoutManager
        binding.ordersRecyclerView.adapter = ordersAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileViewModel.order.observe(viewLifecycleOwner) {
            profileViewModel.updateCurrentOrderItemList(it)
            ordersAdapter.setOrdersData(it)

            if (profileViewModel.currentOrderList.isEmpty()) {
                binding.ordersEmptyTv.visibility = View.VISIBLE
            } else {
                binding.ordersEmptyTv.visibility = View.GONE
            }
            binding.loaderLayout.loaderFrameLayout.visibility = View.GONE
        }
    }

}