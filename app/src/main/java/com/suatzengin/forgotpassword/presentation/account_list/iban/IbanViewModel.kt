package com.suatzengin.forgotpassword.presentation.account_list.iban

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suatzengin.forgotpassword.domain.model.Iban
import com.suatzengin.forgotpassword.domain.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class IbanViewModel @Inject constructor(
    private val repository: LocalRepository

) : ViewModel() {

    private val _state = MutableStateFlow<List<Iban>>(listOf())
    val state: StateFlow<List<Iban>>
        get() = _state

    init {
        getIbanList()
    }

    private fun getIbanList() {
        repository.getIbans().onEach {list ->
            _state.value = list.reversed()
        }.launchIn(viewModelScope)
    }

    fun deleteIban(iban: Iban){
        viewModelScope.launch {
            repository.deleteIban(iban = iban)
        }
    }
}