package com.ilyamarvin.pizzamobileapp.ui.confirmation

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ilyamarvin.pizzamobileapp.R
import com.ilyamarvin.pizzamobileapp.databinding.FragmentConfirmationBinding
import com.ilyamarvin.pizzamobileapp.databinding.FragmentProfileBinding

class ConfirmationFragment : Fragment() {

    private var _binding: FragmentConfirmationBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = ConfirmationFragment()
    }

    private lateinit var viewModel: ConfirmationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConfirmationBinding.inflate(inflater, container, false)

        binding.registrationBtn.setOnClickListener {
            findNavController().navigate(R.id.action_confirmationFragment_to_registrationFragment)
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ConfirmationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}