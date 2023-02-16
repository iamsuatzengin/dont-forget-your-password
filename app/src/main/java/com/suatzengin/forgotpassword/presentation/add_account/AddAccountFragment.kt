package com.suatzengin.forgotpassword.presentation.add_account

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.suatzengin.forgotpassword.R
import com.suatzengin.forgotpassword.base.BaseFragment
import com.suatzengin.forgotpassword.common.checkIsEmpty
import com.suatzengin.forgotpassword.databinding.FragmentAddAccountBinding
import com.suatzengin.forgotpassword.domain.model.Platform
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class AddAccountFragment : BaseFragment<FragmentAddAccountBinding, AddAccountViewModel>() {


    override val viewModel: AddAccountViewModel by viewModels()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAddAccountBinding {
        return FragmentAddAccountBinding.inflate(inflater, container, false)
    }

    override val observeDataBlock: suspend CoroutineScope.() -> Unit = {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowMessage -> {
                    Toast.makeText(requireContext(), event.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    override fun onViewCreated() {
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
            val platformValue = binding.socialContent.typeText.text
            viewModel.setPlatform(platformValue.toString())
        }

        radioGroupOnCheckedListener()

    }

    private fun radioGroupOnCheckedListener() {
        binding.apply {
            radioGroup.setOnCheckedChangeListener { radioGroup, id ->
                buttonSave.visibility = View.VISIBLE
                when (id) {
                    rbSocial.id -> {
                        socialContent.root.visibility = View.VISIBLE
                        ibanContent.root.visibility = View.GONE
                        buttonSave.setOnClickListener {
                            addSocialAccount()
                        }
                    }

                    rbIban.id -> {
                        socialContent.root.visibility = View.GONE
                        ibanContent.root.visibility = View.VISIBLE
                        buttonSave.setOnClickListener {
                            addIban()
                        }
                    }
                }
            }
        }
    }

    private fun addSocialAccount() {
        val email = binding.socialContent.textFieldUsername.checkIsEmpty(
            binding.socialContent.editTextUsername
        )
        val password = binding.socialContent.textFieldPassword.checkIsEmpty(
            binding.socialContent.editTextPassword
        )
        if (email.isNotEmpty() && password.isNotEmpty()) {
            viewModel.setSocialAccount(
                usernameOrEmail = email,
                password = password
            )
        }
    }

    private fun addIban() {
        val owner = binding.ibanContent.textFieldOwner.checkIsEmpty(
            binding.ibanContent.editTextOwner
        )
        val bankName = binding.ibanContent.textFieldBankName.checkIsEmpty(
            binding.ibanContent.editTextBankName
        )
        val ibanNumber = binding.ibanContent.textFieldIbanNumber.checkIsEmpty(
            binding.ibanContent.editTextIbanNumber
        )

        if (owner.isNotEmpty() && bankName.isNotEmpty() && ibanNumber.isNotEmpty()) {
            viewModel.setIban(
                owner = owner,
                bankName = bankName,
                ibanNumber = ibanNumber
            )
        }
    }
}
