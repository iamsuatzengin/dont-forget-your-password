package com.suatzengin.forgotpassword.presentation.add_account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.suatzengin.forgotpassword.R
import com.suatzengin.forgotpassword.databinding.FragmentAddAccountBinding
import com.suatzengin.forgotpassword.domain.model.Platform
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddAccountFragment : Fragment() {
    private var _binding: FragmentAddAccountBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddAccountViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val platforms = listOf(
            Platform.NETFLIX, Platform.SPOTIFY,
            Platform.TWITTER, Platform.INSTAGRAM, Platform.YOUTUBE
        )
        val arrayAdapter =
            ArrayAdapter(requireContext(), R.layout.dropdown_item, platforms.map { it.name })

        binding.socialContent.typeText.setAdapter(arrayAdapter)

        binding.topAppBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.socialContent.typeText.setOnItemClickListener { adapterView, viewx, i, l ->
            val platformValue =binding.socialContent.typeText.text
            Toast.makeText(
                requireContext(),
                "${platformValue}",
                Toast.LENGTH_SHORT
            ).show()
            viewModel.setPlatform(platformValue.toString())
        }


        binding.apply {
            radioGroup.setOnCheckedChangeListener { radioGroup, id ->
                when (id) {
                    rbSocial.id -> {
                        binding.socialContent.root.visibility = View.VISIBLE
                        binding.ibanContent.root.visibility = View.GONE
                        binding.buttonSave.setOnClickListener {
                            addSocialAccount()
                        }
                    }

                    rbIban.id -> {
                        binding.socialContent.root.visibility = View.GONE
                        binding.ibanContent.root.visibility = View.VISIBLE
                        binding.buttonSave.setOnClickListener {
                            addIban()
                        }
                    }
                }
            }
        }


    }

    private fun addSocialAccount() {
        val usernameOrEmail = binding.socialContent.textFieldUsername.editText?.text
        val password = binding.socialContent.textFieldPassword.editText?.text

        viewModel.setSocialAccount(
            usernameOrEmail = usernameOrEmail.toString(),
            password = password.toString()
        )
        viewModel.addSocialAccounts()
    }

    private fun addIban() {
        val owner = binding.ibanContent.tfOwner.editText?.text
        val bankName = binding.ibanContent.tfBankName.editText?.text
        val ibanNumber = binding.ibanContent.tfIbanNumber.editText?.text

        viewModel.setIban(
            owner = owner.toString(),
            bankName = bankName.toString(),
            ibanNumber = ibanNumber.toString()
        )
        viewModel.addIban()
    }
}
