package com.ilyamarvin.pizzamobileapp.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ilyamarvin.pizzamobileapp.R

class AddEditAddressFragment : Fragment() {

    companion object {
        fun newInstance() = AddEditAddressFragment()
    }

//    private lateinit var viewModel: AddEditAddressViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_edit_address, container, false)
    }

}