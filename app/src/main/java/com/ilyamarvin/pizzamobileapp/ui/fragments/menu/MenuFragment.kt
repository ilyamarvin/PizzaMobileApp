package com.ilyamarvin.pizzamobileapp.ui.fragments.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilyamarvin.pizzamobileapp.data.model.CartItem
import com.ilyamarvin.pizzamobileapp.databinding.FragmentMenuBinding
import com.ilyamarvin.pizzamobileapp.data.model.Product
import com.ilyamarvin.pizzamobileapp.ui.fragments.profile.ProfileViewModel

class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding

    private lateinit var menuAdapter: MenuAdapter

    private val menuViewModel: MenuViewModel by activityViewModels()
    private val profileViewModel: ProfileViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMenuBinding.inflate(inflater, container, false)
        menuAdapter = MenuAdapter()

        val layoutManager = LinearLayoutManager(requireContext())
        binding.menuProductsRecyclerView.layoutManager = layoutManager
        binding.menuProductsRecyclerView.adapter = menuAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menuViewModel.products.observe(viewLifecycleOwner) {
            menuViewModel.updateCurrentProductList(it)
            menuAdapter.setProductsData(it)
            binding.loaderLayout.loaderFrameLayout.visibility = View.GONE
        }

        menuAdapter.onProductClickListener = object : MenuAdapter.OnProductClickListener {
            override fun onProductClick(product: Product) {
                findNavController().navigate(
                    MenuFragmentDirections.actionNavigationMenuToProductDetailsFragment(
                        product.id
                    )
                )
            }

            override fun onAddToCartClick(product: Product) {
                TODO("Not yet implemented")
            }

//            override fun onAddToCartClick(cartItem: CartItem) {
//                Toast.makeText(context, "hello", Toast.LENGTH_LONG).show()
////                profileViewModel.addCartItem(cartItem.id)
//
//                Toast.makeText(activity, "${product.name} добавлена в корзину", Toast.LENGTH_SHORT)
//                    .show()
        }
    }
}
