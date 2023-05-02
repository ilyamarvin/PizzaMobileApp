package com.ilyamarvin.pizzamobileapp.ui.fragments.profile.address

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
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

//        binding.confirmEditProfileButton.setOnClickListener {
//            profileViewModel.updateUserData(
//                binding.profileEditName.editText!!.text.toString(),
//                binding.registrationEmail.editText!!.text.toString()
//            )
//            findNavController().popBackStack()
//        }
//
//        addressId = args.addressId
//        val address = profileViewModel.getAddress(addressId)
//
//        binding.productDetailsTitle.text = product.name
//        binding.productDetailsDesc.text = product.description
//
//        binding.loaderLayout.loaderFrameLayout.visibility = View.GONE
//
//        profileViewModel.get().observe(viewLifecycleOwner) {
//
//            binding.profileEditName.editText!!.setText(it.name)
//            binding.registrationEditNumber.editText!!.setText(it.phoneNumber)
//            binding.registrationEmail.editText!!.setText(it.email)
//
//            val birthdate = it.birthdate!!.split(".").toTypedArray()
//
//            binding.registrationDayBirthdateEditProfile.editText!!.setText(birthdate[0])
//            binding.registrationMonthBirthdateEditProfile.editText!!.setText(birthdate[1])
//            binding.registrationYearBirthdateEditProfile.editText!!.setText(birthdate[2])
//        }
//
//        binding.loaderLayout.loaderFrameLayout.visibility = View.GONE
//
//        binding.confirmEditProfileButton.setOnClickListener {
//            profileViewModel.updateUserData(
//                binding.profileEditName.editText!!.text.toString(),
//                binding.registrationEmail.editText!!.text.toString()
//            )
//            findNavController().popBackStack()
//
//        profileViewModel.address.observe(viewLifecycleOwner) {
//
//
//            val birthdate = it.birthdate!!.split(".").toTypedArray()
//
//            binding.registrationDayBirthdateEditProfile.editText!!.setText(birthdate[0])
//            binding.registrationMonthBirthdateEditProfile.editText!!.setText(birthdate[1])
//            binding.registrationYearBirthdateEditProfile.editText!!.setText(birthdate[2])
//        }
//
//        binding.confirmEditProfileButton.setOnClickListener {
//            profileViewModel.updateUserData(
//                binding.profileEditName.editText!!.text.toString(),
//                binding.registrationEmail.editText!!.text.toString()
//            )
//            findNavController().popBackStack()
//        }
    }

}