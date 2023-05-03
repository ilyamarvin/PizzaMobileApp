package com.ilyamarvin.pizzamobileapp.ui.fragments.cart

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilyamarvin.pizzamobileapp.R
import com.ilyamarvin.pizzamobileapp.data.model.Address
import com.ilyamarvin.pizzamobileapp.data.model.Product
import com.ilyamarvin.pizzamobileapp.databinding.FragmentSelectAddressBinding
import com.ilyamarvin.pizzamobileapp.ui.fragments.profile.ProfileViewModel
import java.text.DateFormat

class SelectAddressFragment : Fragment() {

    private lateinit var binding: FragmentSelectAddressBinding
    private lateinit var selectAddressAdapter: SelectAddressAdapter
    private lateinit var cartAdapter: CartAdapter

    private val profileViewModel: ProfileViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSelectAddressBinding.inflate(inflater, container, false)
        selectAddressAdapter = SelectAddressAdapter()
        cartAdapter = CartAdapter()

        val layoutManager = LinearLayoutManager(requireContext())
        binding.selectAddressRecyclerView.layoutManager = layoutManager
        binding.selectAddressRecyclerView.adapter = selectAddressAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileViewModel.address.observe(viewLifecycleOwner) {
            profileViewModel.updateCurrentAddressList(it)
            selectAddressAdapter.setAddressData(it)

        }

        binding.loaderLayout.loaderFrameLayout.visibility = View.GONE
        binding.addressesEmpty.visibility = View.GONE

        selectAddressAdapter.onSelectAddressClickListener =
            object : SelectAddressAdapter.OnSelectAddressClickListener {
                override fun onSelectAddressClick(address: Address) {
                    val cartItems = profileViewModel.cartItems.value

                    val productList: MutableList<Product> = mutableListOf()
                    for (cart in cartItems!!) {
                        productList += cart.product
                    }
                    val totalSum = productList.sumOf { it.price }
                    profileViewModel.addOrder(
                        totalSum,
                        DateFormat.getDateTimeInstance().format(System.currentTimeMillis()),
                        address,
                        productList
                    )
                    profileViewModel.clearCart()
                    cartAdapter.clearCart()
                    findNavController().navigate(R.id.action_selectAddressFragment_to_orderSuccessFragment)
                }
            }
    }
}