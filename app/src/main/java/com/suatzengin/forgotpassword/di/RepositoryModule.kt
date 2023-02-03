package com.suatzengin.forgotpassword.di

import com.suatzengin.forgotpassword.data.repository.LocalRepositoryImpl
import com.suatzengin.forgotpassword.domain.repository.LocalRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindLocalRepository(localRepositoryImpl: LocalRepositoryImpl): LocalRepository
}