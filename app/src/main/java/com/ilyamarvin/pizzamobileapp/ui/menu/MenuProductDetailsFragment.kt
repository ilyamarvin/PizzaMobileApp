package com.ilyamarvin.pizzamobileapp.ui.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.ilyamarvin.pizzamobileapp.databinding.FragmentProductDetailsBinding
import com.ilyamarvin.pizzamobileapp.data.model.Product
import com.ilyamarvin.pizzamobileapp.viewmodel.MenuViewModel

private const val PRODUCT_ID = "productId"

class MenuProductDetailsFragment : Fragment() {

    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var menuViewModel: MenuViewModel
//    private lateinit var cartViewModel: CartViewModel

    private var productId: Int = 1
    private lateinit var product: Product

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)

        productId = arguments?.getInt(PRODUCT_ID)!!

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewModel()
        product = menuViewModel.getProduct(productId)
        bindProductView()
        bindAddProductButton()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getViewModel() {
        val application = requireNotNull(this.activity).application
        menuViewModel = MenuViewModel.getInstance(application)
    }

    private fun bindProductView() {
        binding.productDetailsTitle.text = product.name
        binding.productDetailsDesc.text = product.description
        binding.productDetailsPrice.text = product.price
        Glide.with(binding.productDetailsImageView)
            .load(product.link)
            .skipMemoryCache(true)
            .into(binding.productDetailsImageView)
    }

    private fun bindAddProductButton() {
        binding.addProductDetailsToCartBtn.setOnClickListener {
//            cartViewModel.addProductToCart(product.id)
            Toast.makeText(activity, "${product.name} добавлена в корзину", Toast.LENGTH_LONG)
                .show()
            this.findNavController().popBackStack()
        }
    }

}