package com.suatzengin.forgotpassword.presentation.add_account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suatzengin.forgotpassword.domain.model.Iban
import com.suatzengin.forgotpassword.domain.model.Platform
import com.suatzengin.forgotpassword.domain.model.SocialAccount
import com.suatzengin.forgotpassword.domain.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddAccountViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AddAccountUiState())
    val state: StateFlow<AddAccountUiState>
        get() = _state

    fun addSocialAccounts() {
        if (_state.value.socialAccount == null) {
            _state.update {
                it.copy(message = "It must not be empty!")
            }
            return
        }
        viewModelScope.launch {
            localRepository.addAccount(socialAccount = _state.value.socialAccount!!)
        }
    }

    fun setSocialAccount(usernameOrEmail: String, password: String) {
        val socialAccount = SocialAccount(
            usernameOrEmail = usernameOrEmail,
            password = password,
            platform = _state.value.platform!!
        )
        _state.update { it.copy(socialAccount = socialAccount) }
    }

    fun addIban(){
        if (_state.value.iban == null) {
            _state.update {
                it.copy(message = "It must not be empty!")
            }
            return
        }
        viewModelScope.launch {
            localRepository.addIban(iban = _state.value.iban!!)
        }
    }

    fun setIban(owner: String, bankName: String, ibanNumber: String){
        val iban = Iban(
            owner = owner,
            bank = bankName,
            ibanNumber = ibanNumber
        )
        _state.update { it.copy(iban = iban) }
    }

    fun setPlatform(value: String){
        val platformValue = enumValueOf<Platform>(value)
        _state.update { it.copy(platform = platformValue) }
    }
}

data class AddAccountUiState(
    val socialAccount: SocialAccount? = null,
    val iban: Iban? = null,
    val message: String = "",
    val platform: Platform? = null
)