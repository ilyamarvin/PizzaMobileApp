package com.ilyamarvin.pizzamobileapp.ui.fragments.profile.address

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isEmpty
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ilyamarvin.pizzamobileapp.data.model.Address
import com.ilyamarvin.pizzamobileapp.databinding.FragmentAddEditAddressBinding
import com.ilyamarvin.pizzamobileapp.ui.fragments.profile.ProfileViewModel

class AddEditAddressFragment : Fragment() {

    private lateinit var binding: FragmentAddEditAddressBinding

    private val args by navArgs<AddEditAddressFragmentArgs>()

    private val profileViewModel: ProfileViewModel by activityViewModels()

    private var addressId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAddEditAddressBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addressId = args.addressId

        if (addressId != 0) {
            val address = profileViewModel.getAddress(addressId)
            bindAddressEditView(address)
            bindEditAddressButton(address)
        } else {
            bindAddAddressButton()
        }
        binding.loaderLayout.loaderFrameLayout.visibility = View.GONE
    }

    private fun bindEditAddressButton(address: Address) {
        binding.addEditAddressSaveBtn.setOnClickListener {

            if (binding.streetAddEditTextField.editText?.text.toString().isEmpty()) {
                binding.streetAddEditTextField.error = "Это поле не может быть пустым"
                binding.streetAddEditTextField.requestFocus()
                return@setOnClickListener
            } else binding.streetAddEditTextField.error = null

            if (binding.apartmentAddEditTextField.editText?.text.toString().isEmpty()) {
                binding.apartmentAddEditTextField.error = "Это поле не может быть пустым"
                binding.apartmentAddEditTextField.requestFocus()
                return@setOnClickListener
            } else binding.apartmentAddEditTextField.error = null

            if (binding.floorAddEditTextField.editText?.text.toString().isEmpty()) {
                binding.floorAddEditTextField.error = "Это поле не может быть пустым"
                binding.floorAddEditTextField.requestFocus()
                return@setOnClickListener
            } else binding.floorAddEditTextField.error = null

            if (binding.entranceAddEditTextField.editText?.text.toString().isEmpty()) {
                binding.entranceAddEditTextField.error = "Это поле не может быть пустым"
                binding.entranceAddEditTextField.requestFocus()
                return@setOnClickListener
            } else binding.entranceAddEditTextField.error = null

            if (binding.intercomAddEditTextField.editText?.text.toString().isEmpty()) {
                binding.intercomAddEditTextField.error = "Это поле не может быть пустым"
                binding.intercomAddEditTextField.requestFocus()
                return@setOnClickListener
            } else binding.intercomAddEditTextField.error = null

            if (binding.commentAddEditTextField.editText?.text.toString().isEmpty()) {
                binding.commentAddEditTextField.error = "Это поле не может быть пустым"
                binding.commentAddEditTextField.requestFocus()
                return@setOnClickListener
            } else binding.commentAddEditTextField.error = null

            profileViewModel.updateAddress(
                address.id,
                binding.streetAddEditTextField.editText!!.text.toString(),
                binding.apartmentAddEditTextField.editText!!.text.toString().toInt(),
                binding.floorAddEditTextField.editText!!.text.toString().toInt(),
                binding.entranceAddEditTextField.editText!!.text.toString().toInt(),
                binding.intercomAddEditTextField.editText!!.text.toString().toInt(),
                binding.commentAddEditTextField.editText!!.text.toString()
            )
            findNavController().popBackStack()
        }
    }

    private fun bindAddAddressButton() {
        binding.addEditAddressSaveBtn.setOnClickListener {

            if (binding.streetAddEditTextField.editText?.text.toString().isEmpty()) {
                binding.streetAddEditTextField.error = "Это поле не может быть пустым"
                binding.streetAddEditTextField.requestFocus()
                return@setOnClickListener
            } else binding.streetAddEditTextField.error = null

            if (binding.apartmentAddEditTextField.editText?.text.toString().isEmpty()) {
                binding.apartmentAddEditTextField.error = "Это поле не может быть пустым"
                binding.apartmentAddEditTextField.requestFocus()
                return@setOnClickListener
            } else binding.apartmentAddEditTextField.error = null

            if (binding.floorAddEditTextField.editText?.text.toString().isEmpty()) {
                binding.floorAddEditTextField.error = "Это поле не может быть пустым"
                binding.floorAddEditTextField.requestFocus()
                return@setOnClickListener
            } else binding.floorAddEditTextField.error = null

            if (binding.entranceAddEditTextField.editText?.text.toString().isEmpty()) {
                binding.entranceAddEditTextField.error = "Это поле не может быть пустым"
                binding.entranceAddEditTextField.requestFocus()
                return@setOnClickListener
            } else binding.entranceAddEditTextField.error = null

            if (binding.intercomAddEditTextField.editText?.text.toString().isEmpty()) {
                binding.intercomAddEditTextField.error = "Это поле не может быть пустым"
                binding.intercomAddEditTextField.requestFocus()
                return@setOnClickListener
            } else binding.intercomAddEditTextField.error = null

            if (binding.commentAddEditTextField.editText?.text.toString().isEmpty()) {
                binding.commentAddEditTextField.error = "Это поле не может быть пустым"
                binding.commentAddEditTextField.requestFocus()
                return@setOnClickListener
            } else binding.commentAddEditTextField.error = null

            profileViewModel.addAddress(
                binding.streetAddEditTextField.editText!!.text.toString(),
                binding.apartmentAddEditTextField.editText!!.text.toString().toInt(),
                binding.floorAddEditTextField.editText!!.text.toString().toInt(),
                binding.entranceAddEditTextField.editText!!.text.toString().toInt(),
                binding.intercomAddEditTextField.editText!!.text.toString().toInt(),
                binding.commentAddEditTextField.editText!!.text.toString()
            )
            findNavController().popBackStack()
        }
    }

    private fun bindAddressEditView(address: Address) {
        binding.streetAddEditTextField.editText!!.setText(address.street)
        binding.apartmentAddEditTextField.editText!!.setText(address.apartment.toString())
        binding.floorAddEditTextField.editText!!.setText(address.floor.toString())
        binding.entranceAddEditTextField.editText!!.setText(address.entrance.toString())
        binding.intercomAddEditTextField.editText!!.setText(address.intercom.toString())
        binding.commentAddEditTextField.editText!!.setText(address.comment)
    }
}