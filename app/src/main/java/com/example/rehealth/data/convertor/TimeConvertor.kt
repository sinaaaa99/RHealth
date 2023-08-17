package com.example.rehealth.data.convertor

import androidx.room.TypeConverter
import java.time.LocalDateTime

class TimeConvertor {

    @TypeConverter
    fun timeToString(time: LocalDateTime): String {

        return time.toString()

    }

    @TypeConverter
    fun stringToTime(value: String): LocalDateTime {

        return LocalDateTime.parse(value)
    }
}