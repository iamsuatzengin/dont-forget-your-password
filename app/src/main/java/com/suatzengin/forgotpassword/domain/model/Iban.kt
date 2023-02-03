package com.suatzengin.forgotpassword.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "iban")
data class Iban(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val owner: String,
    val bank: String,
    val ibanNumber: String
)
