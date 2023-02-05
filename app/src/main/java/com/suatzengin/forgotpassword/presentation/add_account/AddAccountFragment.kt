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
    private fun radioGroupOnCheckedListener(){
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
        val usernameOrEmail = binding.socialContent.textFieldUsername.editText?.text.toString()
        val password = binding.socialContent.textFieldPassword.editText?.text.toString()

        viewModel.setSocialAccount(
            usernameOrEmail = usernameOrEmail,
            password = password
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
