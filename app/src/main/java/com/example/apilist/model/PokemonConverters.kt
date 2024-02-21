package com.example.apilist.model

import androidx.room.TypeConverter

class PokemonConverters {
    @TypeConverter
    fun fromStringListToString(list: List<String>): String {
        return list.joinToString(",")
    }
    @TypeConverter
    fun fromStringToStringList(string: String): List<String> {
        return string.split(",")
    }


    @TypeConverter
    fun fromImageToString(image: Images): String {
        return "${image.large},${image.small}"
    }
    @TypeConverter
    fun fromStringToImage(string: String): Images {
        val large = string.split(",")[0]
        val small = string.split(", ")[1]
        return Images(large,small)
    }

}