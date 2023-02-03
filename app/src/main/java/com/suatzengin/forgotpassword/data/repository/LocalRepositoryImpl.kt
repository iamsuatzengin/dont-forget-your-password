package com.suatzengin.forgotpassword.data.repository

import com.suatzengin.forgotpassword.data.local.IbanDao
import com.suatzengin.forgotpassword.data.local.SocialAccountDao
import com.suatzengin.forgotpassword.domain.model.Iban
import com.suatzengin.forgotpassword.domain.model.SocialAccount
import com.suatzengin.forgotpassword.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val socialAccountDao: SocialAccountDao,
    private val ibanDao: IbanDao
) : LocalRepository {

    override fun getAllAccounts(): Flow<List<SocialAccount>> = socialAccountDao.gettAllAccount()

    override suspend fun addAccount(socialAccount: SocialAccount) {
        return socialAccountDao.addAccount(socialAccount = socialAccount)
    }

    override suspend fun deleteAccount(socialAccount: SocialAccount) {
        return socialAccountDao.deleteAccount(socialAccount = socialAccount)
    }

    override fun getIbans(): Flow<List<Iban>> = ibanDao.getIbans()

    override suspend fun addIban(iban: Iban) = ibanDao.addIban(iban = iban)

    override suspend fun deleteIban(iban: Iban) = ibanDao.deleteIban(iban = iban)
}