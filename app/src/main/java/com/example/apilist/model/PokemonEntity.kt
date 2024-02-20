package com.example.apilist.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PokemonEntity")
data class PokemonEntity(
    @PrimaryKey
    val id: String,
    val name: String
)