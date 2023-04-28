package com.ilyamarvin.pizzamobileapp.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ilyamarvin.pizzamobileapp.R
import com.ilyamarvin.pizzamobileapp.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    private lateinit var productRecyclerView: RecyclerView
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

        menuViewModel = ViewModelProvider(this).get(MenuViewModel::class.java)

        productRecyclerView = binding.menuProductsRecyclerView
        productRecyclerView.layoutManager = LinearLayoutManager(context)

        menuAdapter = MenuAdapter()

        productRecyclerView.adapter = menuAdapter

        menuViewModel.allProducts.observe(viewLifecycleOwner, Observer {
            menuAdapter.loadProducts(it)
            binding.loaderLayout.loaderFrameLayout.visibility = View.GONE
        })

        binding.menuProductsRecyclerView.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_menu_to_productDetailsFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}