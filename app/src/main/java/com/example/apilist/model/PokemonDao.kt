package com.example.apilist.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PokemonDao {
    @Query("SELECT * FROM PokemonEntity")
    suspend fun getAllCharacters(): MutableList<Data>
    @Query("SELECT * FROM PokemonEntity WHERE id = :pokemonId")
    suspend fun getCharacterById(pokemonId: String): MutableList<Data>
    @Insert
    suspend fun addCharacter(pokemon: Data)
    @Delete
    suspend fun deleteCharacter(pokemon: Data)
}