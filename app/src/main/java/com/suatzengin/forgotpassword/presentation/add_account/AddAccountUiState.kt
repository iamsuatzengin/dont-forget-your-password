package com.suatzengin.forgotpassword.presentation.add_account

import com.suatzengin.forgotpassword.domain.model.Iban
import com.suatzengin.forgotpassword.domain.model.Platform
import com.suatzengin.forgotpassword.domain.model.SocialAccount

data class AddAccountUiState(
    val socialAccount: SocialAccount? = null,
    val iban: Iban? = null,
    val message: String = "",
    val platform: Platform? = null
)