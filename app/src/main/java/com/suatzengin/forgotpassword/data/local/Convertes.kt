package com.suatzengin.forgotpassword.data.local

import androidx.room.TypeConverter
import com.suatzengin.forgotpassword.domain.model.Platform

class Converters {

    @TypeConverter
    fun toPlatform(value: String): Platform = enumValueOf<Platform>(value)

    @TypeConverter
    fun fromPlatform(value: Platform): String = value.name
}