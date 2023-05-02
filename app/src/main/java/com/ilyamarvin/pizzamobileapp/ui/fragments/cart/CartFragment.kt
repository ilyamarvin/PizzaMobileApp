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
import com.ilyamarvin.pizzamobileapp.databinding.FragmentCartBinding
import com.ilyamarvin.pizzamobileapp.ui.fragments.menu.MenuViewModel

class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding

    private lateinit var cartAdapter: CartAdapter

    private val menuViewModel: MenuViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
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

        bindLiveData()
        bindClickListeners()
    }

    private fun bindLiveData() {
//        menuViewModel.cartItems.observe(viewLifecycleOwner) {
//            val productList = menuViewModel.getCurrentCartList(it)
//            cartAdapter.setCartItems(it)
//            if (productList.isEmpty()) {
//                binding.cartEmptyTitle.visibility = View.VISIBLE
//                binding.cartCheckoutBtn.visibility = View.INVISIBLE
//                binding.cartGoForOrdersBtn.visibility = View.VISIBLE
//            } else {
//                binding.cartEmptyTitle.visibility = View.INVISIBLE
//                binding.cartCheckoutBtn.visibility = View.VISIBLE
//                binding.cartGoForOrdersBtn.visibility = View.INVISIBLE
//            }
//            binding.loaderLayout.loaderFrameLayout.visibility = View.GONE
//        }
    }

    private fun bindClickListeners() {
        cartAdapter.onCartClickListener = object : CartAdapter.OnCartClickListener {
            override fun removeCartItem(productId: Int) {
//                menuViewModel.removeCartItem(productId)
                cartAdapter.removeCartItem(productId)
            }
        }
        binding.cartGoForOrdersBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.cartCheckoutBtn.setOnClickListener {
//            menuViewModel.clearCart()
            Toast.makeText(activity, "Спасибо за заказ!", Toast.LENGTH_SHORT).show()
        }
    }
}