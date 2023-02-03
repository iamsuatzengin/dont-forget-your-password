package com.suatzengin.forgotpassword.di

import android.content.Context
import androidx.room.Room
import com.suatzengin.forgotpassword.data.local.AppDatabase
import com.suatzengin.forgotpassword.data.local.IbanDao
import com.suatzengin.forgotpassword.data.local.SocialAccountDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase{
        return Room.databaseBuilder(
            context = context,
            klass = AppDatabase::class.java,
            name = "forgot_password_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideSocialAccountDao(
        db: AppDatabase
    ): SocialAccountDao = db.socialAccountDao()

    @Singleton
    @Provides
    fun provideIbanDao(
        db: AppDatabase
    ): IbanDao = db.ibanDao()

}












