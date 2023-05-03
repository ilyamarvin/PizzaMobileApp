package com.ilyamarvin.pizzamobileapp.ui.fragments.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilyamarvin.pizzamobileapp.R
import com.ilyamarvin.pizzamobileapp.data.model.CartItem
import com.ilyamarvin.pizzamobileapp.databinding.FragmentCartBinding
import com.ilyamarvin.pizzamobileapp.ui.fragments.profile.ProfileViewModel

class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
    private lateinit var cartAdapter: CartAdapter

    private val profileViewModel: ProfileViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        binding = FragmentCartBinding.inflate(inflater, container, false)
        cartAdapter = CartAdapter()

        val layoutManager = LinearLayoutManager(requireContext())
        binding.cartProductsRecyclerView.layoutManager = layoutManager
        binding.cartProductsRecyclerView.adapter = cartAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileViewModel.cartItems.observe(viewLifecycleOwner) {
            profileViewModel.updateCurrentCartItemList(it)
            cartAdapter.setCartItems(it)

            if (profileViewModel.currentCartItemList.isEmpty()) {
                binding.cartEmptyTitle.visibility = View.VISIBLE
                binding.cartGoForOrdersBtn.visibility = View.VISIBLE
                binding.cartCheckoutBtn.visibility = View.GONE
            } else {
                binding.cartEmptyTitle.visibility = View.GONE
                binding.cartGoForOrdersBtn.visibility = View.GONE
                binding.cartCheckoutBtn.visibility = View.VISIBLE
            }
            binding.loaderLayout.loaderFrameLayout.visibility = View.GONE
        }
        bindClickListeners()
    }

    private fun bindClickListeners() {
        cartAdapter.onCartClickListener = object : CartAdapter.OnCartClickListener {
            override fun removeCartItem(cartItem: CartItem) {
                profileViewModel.deleteCartItem(cartItem)
            }
        }
        binding.cartGoForOrdersBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.cartCheckoutBtn.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_cart_to_selectAddressFragment)
        }
    }
}