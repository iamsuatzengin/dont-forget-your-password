package com.suatzengin.forgotpassword.presentation.add_account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suatzengin.forgotpassword.domain.model.Iban
import com.suatzengin.forgotpassword.domain.model.Platform
import com.suatzengin.forgotpassword.domain.model.SocialAccount
import com.suatzengin.forgotpassword.domain.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.lang.NullPointerException
import javax.inject.Inject

@HiltViewModel
class AddAccountViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AddAccountUiState())
    val state: StateFlow<AddAccountUiState>
        get() = _state

    private val _eventChannel = Channel<UiEvent>(Channel.BUFFERED)
    val eventFlow = _eventChannel.receiveAsFlow()

    fun addSocialAccounts() {
        viewModelScope.launch {
            try {
                localRepository.addAccount(socialAccount = _state.value.socialAccount!!)
                _eventChannel.send(UiEvent.ShowMessage(message = "Successfully added!"))
            } catch (e: Exception) {
                _eventChannel.send(UiEvent.ShowMessage(message = "An error occured!"))
            } catch (e: NullPointerException) {
                _eventChannel.send(UiEvent.ShowMessage(message = "An error occured! ${e.localizedMessage}"))
            }
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

    fun addIban() {
        viewModelScope.launch {
            try {
                localRepository.addIban(iban = _state.value.iban!!)
                _eventChannel.send(UiEvent.ShowMessage(message = "Successfully added!"))
            } catch (e: Exception) {
                _eventChannel.send(UiEvent.ShowMessage(message = "An error occured!"))
            }
        }
    }

    fun setIban(owner: String, bankName: String, ibanNumber: String) {
        val iban = Iban(
            owner = owner,
            bank = bankName,
            ibanNumber = ibanNumber
        )
        _state.update { it.copy(iban = iban) }
    }

    fun setPlatform(value: String) {
        val platformValue = enumValueOf<Platform>(value)
        _state.update { it.copy(platform = platformValue) }

    }
}

sealed class UiEvent {
    data class ShowMessage(val message: String) : UiEvent()
}