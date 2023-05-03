package com.ilyamarvin.pizzamobileapp.ui.fragments.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ilyamarvin.pizzamobileapp.R
import com.ilyamarvin.pizzamobileapp.databinding.FragmentEditProfileBinding
import com.ilyamarvin.pizzamobileapp.databinding.FragmentProfileBinding

class EditProfileFragment : Fragment() {

    private lateinit var binding: FragmentEditProfileBinding

    private val profileViewModel: ProfileViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentEditProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileViewModel.getUserData().observe(viewLifecycleOwner) {

            binding.profileEditName.editText!!.setText(it.name)
            binding.registrationEditNumber.editText!!.setText(it.phoneNumber)
            binding.registrationEmail.editText!!.setText(it.email)

            val birthdate = it.birthdate!!.split(".").toTypedArray()

            binding.registrationDayBirthdateEditProfile.editText!!.setText(birthdate[0])
            binding.registrationMonthBirthdateEditProfile.editText!!.setText(birthdate[1])
            binding.registrationYearBirthdateEditProfile.editText!!.setText(birthdate[2])

            binding.loaderLayout.loaderFrameLayout.visibility = View.GONE
        }


        binding.confirmEditProfileButton.setOnClickListener {
            profileViewModel.updateUserData(
                binding.profileEditName.editText!!.text.toString(),
                binding.registrationEmail.editText!!.text.toString()
            )
            findNavController().popBackStack()
        }
    }

}