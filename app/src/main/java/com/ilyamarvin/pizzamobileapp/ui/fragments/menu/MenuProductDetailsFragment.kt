package com.ilyamarvin.pizzamobileapp.ui.fragments.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.ilyamarvin.pizzamobileapp.data.model.Product
import com.ilyamarvin.pizzamobileapp.databinding.FragmentProductDetailsBinding

class MenuProductDetailsFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailsBinding

    private val args by navArgs<MenuProductDetailsFragmentArgs>()

    private val menuViewModel: MenuViewModel by activityViewModels()

    private var productId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productId = args.productId
        val product = menuViewModel.getProduct(productId)
        bindProductView(product)
        bindAddProductButton(product)

    }

    private fun bindProductView(product: Product) {
        binding.productDetailsTitle.text = product.name
        binding.productDetailsDesc.text = product.description
        binding.productDetailsPrice.text = product.price.toString().plus(" ₽")
        Glide.with(binding.productDetailsImageView)
            .load(product.image)
            .skipMemoryCache(true)
            .into(binding.productDetailsImageView)
    }

    private fun bindAddProductButton(product: Product) {
        binding.addProductDetailsToCartBtn.setOnClickListener {
//            menuViewModel.addProductToCart(product)
            Toast.makeText(activity, "${product.name} добавлена в корзину", Toast.LENGTH_LONG)
                .show()
            this.findNavController().popBackStack()
        }
    }

}