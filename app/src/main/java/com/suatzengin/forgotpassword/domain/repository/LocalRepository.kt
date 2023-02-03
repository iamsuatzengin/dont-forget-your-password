package com.suatzengin.forgotpassword.domain.repository

import com.suatzengin.forgotpassword.domain.model.Iban
import com.suatzengin.forgotpassword.domain.model.SocialAccount
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    // accounts
    fun getAllAccounts(): Flow<List<SocialAccount>>
    suspend fun addAccount(socialAccount: SocialAccount)
    suspend fun deleteAccount(socialAccount: SocialAccount)

    // Ibans
    fun getIbans(): Flow<List<Iban>>
    suspend fun addIban(iban: Iban)
    suspend fun deleteIban(iban: Iban)
}