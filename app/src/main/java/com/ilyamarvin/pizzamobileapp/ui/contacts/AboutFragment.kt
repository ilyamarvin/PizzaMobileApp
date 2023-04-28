package com.ilyamarvin.pizzamobileapp.ui.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ilyamarvin.pizzamobileapp.R
import com.ilyamarvin.pizzamobileapp.databinding.FragmentAboutBinding
import com.ilyamarvin.pizzamobileapp.databinding.FragmentConfirmationBinding

class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)

        return binding.root
    }
}