package com.suatzengin.forgotpassword.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "social_account")
data class SocialAccount(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "username_or_email") val usernameOrEmail: String,
    val password: String,
    val platform: Platform
)
