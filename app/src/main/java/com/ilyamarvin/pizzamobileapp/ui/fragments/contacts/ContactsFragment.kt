package com.ilyamarvin.pizzamobileapp.ui.fragments.contacts

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ilyamarvin.pizzamobileapp.R
import com.ilyamarvin.pizzamobileapp.databinding.FragmentContactsBinding


class ContactsFragment : Fragment() {

    private var _binding: FragmentContactsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentContactsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonEmail.setOnClickListener {
            val email = Intent(
                Intent.ACTION_SENDTO, Uri.fromParts("mailto", "ilya.sereda2002@mail.ru", null)
            )
            startActivity(Intent.createChooser(email, "Выберите приложение для отправки письма"));
        }

        binding.buttonAbout.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_contacts_to_navigation_about)
        }
    }
}