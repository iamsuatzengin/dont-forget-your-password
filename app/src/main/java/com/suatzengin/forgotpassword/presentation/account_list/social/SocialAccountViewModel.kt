package com.suatzengin.forgotpassword.presentation.account_list.social

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suatzengin.forgotpassword.domain.model.SocialAccount
import com.suatzengin.forgotpassword.domain.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SocialAccountViewModel @Inject constructor(
    private val repository: LocalRepository
) : ViewModel() {

    private val _state = MutableStateFlow<List<SocialAccount>>(listOf())
    val state: StateFlow<List<SocialAccount>>
        get() = _state

    init {
        getSocialAccountList()
    }

    private fun getSocialAccountList() {
        repository.getAllAccounts().onEach { list ->
                _state.value = list.reversed()
        }.launchIn(viewModelScope)
    }

    fun deleteSocialAccount(socialAccount: SocialAccount){
        viewModelScope.launch {
            repository.deleteAccount(socialAccount = socialAccount)
        }
    }
}
