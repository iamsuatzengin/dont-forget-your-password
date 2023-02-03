package com.suatzengin.forgotpassword.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.suatzengin.forgotpassword.domain.model.SocialAccount
import kotlinx.coroutines.flow.Flow

@Dao
interface SocialAccountDao {

    @Query("SELECT * FROM social_account")
    fun gettAllAccount(): Flow<List<SocialAccount>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAccount(socialAccount: SocialAccount)

    @Delete
    suspend fun deleteAccount(socialAccount: SocialAccount)
}