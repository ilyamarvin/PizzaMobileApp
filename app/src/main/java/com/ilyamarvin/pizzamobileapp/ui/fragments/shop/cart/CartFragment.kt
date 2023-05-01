package com.ilyamarvin.pizzamobileapp.ui.fragments.shop.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ilyamarvin.pizzamobileapp.databinding.FragmentCartBinding
import com.ilyamarvin.pizzamobileapp.ui.fragments.shop.menu.MenuViewModel

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    //    private lateinit var cartViewModel: CartViewModel
    private lateinit var cartViewModel: MenuViewModel
    private lateinit var cartListAdapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCartBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        getViewModel()
//        bindRecyclerView()
//        bindLiveData()
//        bindClickListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    private fun getViewModel() {
//        val application = requireNotNull(this.activity).application
//        cartViewModel = MenuViewModel.getInstance(application)
//    }
//
//    private fun bindLiveData() {
//        cartViewModel.cart.observe(viewLifecycleOwner) {
//            val productList = cartViewModel.getProductsFromIds(it)
//            cartListAdapter.setCartItems(it)
////            Toast.makeText(activity, "$productList", Toast.LENGTH_LONG).show()
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
//    }
//
//    private fun bindRecyclerView() {
//        cartListAdapter = CartListAdapter()
//        val recyclerCartList = binding.cartProductsRecyclerView
//        recyclerCartList.adapter = cartListAdapter
//    }
//
//    private fun bindClickListeners() {
//        cartListAdapter.onCartClickListener = object : CartListAdapter.OnCartClickListener {
//            override fun removeItemFromCart(productId: Int) {
//                cartViewModel.removeItemById(productId)
//                cartListAdapter.removeCartItem(productId)
//            }
//        }
//        binding.cartGoForOrdersBtn.setOnClickListener {
//            findNavController().popBackStack()
//        }
//        binding.cartCheckoutBtn.setOnClickListener {
//            cartViewModel.clearCart()
//            Toast.makeText(activity, "Спасибо за заказ!", Toast.LENGTH_SHORT).show()
//        }
//    }
}