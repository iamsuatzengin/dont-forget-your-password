package com.suatzengin.forgotpassword.common

sealed class UiEvent {
    data class ShowMessage(val message: String) : UiEvent()
}