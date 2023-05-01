package com.ilyamarvin.pizzamobileapp.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ilyamarvin.pizzamobileapp.adapter.MenuAdapter
import com.ilyamarvin.pizzamobileapp.databinding.FragmentMenuBinding
import com.ilyamarvin.pizzamobileapp.data.model.Product
import com.ilyamarvin.pizzamobileapp.viewmodel.MenuViewModel

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    private lateinit var menuViewModel: MenuViewModel
    private lateinit var menuAdapter: MenuAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMenuBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewModel()
        bindLiveData()
        bindProductRecyclerView()
        bindClickListeners()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getViewModel() {
        val application = requireNotNull(this.activity).application
        menuViewModel = MenuViewModel.getInstance(application)
    }

    private fun bindLiveData() {
        menuViewModel.productList.observe(viewLifecycleOwner) {
            menuViewModel.updateCurrentProductList(it)
            menuAdapter.setProductsData(it)
            binding.loaderLayout.loaderFrameLayout.visibility = View.GONE
        }
    }

    private fun bindProductRecyclerView() {
        menuAdapter = MenuAdapter()
        val recyclerProducts = binding.menuProductsRecyclerView
        recyclerProducts.adapter = menuAdapter
    }

    private fun bindClickListeners() {
        menuAdapter.onClickListener = object : MenuAdapter.OnClickListener {
            override fun onClick(product: Product) {
                findNavController().navigate(
                    MenuFragmentDirections.actionNavigationMenuToProductDetailsFragment(
                        product.id
                    )
                )
            }

            override fun onAddToCartClick(product: Product) {
//                menuViewModel.addProductToCart(product.id!!)
//                Toast.makeText(activity, "${product.name} добавлена в корзину", Toast.LENGTH_SHORT)
//                    .show()
            }
        }
    }
}