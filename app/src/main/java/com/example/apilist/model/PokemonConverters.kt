package com.example.apilist.model

import androidx.room.TypeConverter

class PokemonConverters {
    @TypeConverter
    fun fromListToString(list: List<String>): String {
        return list.joinToString(",")
    }
    @TypeConverter
    fun fromStringToList(string: String): List<String> {
        return string.split(",")
    }
}