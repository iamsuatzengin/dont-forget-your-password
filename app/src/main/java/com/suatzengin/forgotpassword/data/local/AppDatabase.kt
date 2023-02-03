package com.suatzengin.forgotpassword.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.suatzengin.forgotpassword.domain.model.Iban
import com.suatzengin.forgotpassword.domain.model.SocialAccount

@Database(entities = [SocialAccount::class, Iban::class], version = 2)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun socialAccountDao(): SocialAccountDao
    abstract fun ibanDao(): IbanDao
}