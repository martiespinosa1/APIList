package com.example.apilist.model

import androidx.room.TypeConverter

class PokemonConverters {
    @TypeConverter
    fun fromStringListToString(list: List<String>?): String {
        if (list != null) {
            return list.joinToString(",")
        } else {
            return ""
        }
    }
    @TypeConverter
    fun fromStringToStringList(string: String): List<String> {
        if (string != null) {
            return string.split(",")
        } else {
            return emptyList()
        }
    }


    @TypeConverter
    fun fromImageToString(image: Images): String {
        return "${image.large},${image.small}"
    }
    @TypeConverter
    fun fromStringToImage(string: String): Images {
        val large = string.split(",")[0]
        val small = string.split(",")[1]
        return Images(large,small)
    }

}