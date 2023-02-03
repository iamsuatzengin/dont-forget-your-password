package com.suatzengin.forgotpassword.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.suatzengin.forgotpassword.domain.model.Iban
import kotlinx.coroutines.flow.Flow

@Dao
interface IbanDao {

    @Query("SELECT * FROM iban")
    fun getIbans(): Flow<List<Iban>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addIban(iban: Iban)

    @Delete
    suspend fun deleteIban(iban: Iban)
}