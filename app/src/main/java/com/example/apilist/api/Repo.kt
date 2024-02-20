package com.example.apilist.api

import com.example.apilist.model.PokemonAplication
import com.example.apilist.model.PokemonEntity

class Repository {

    val apiInterface = APIInterface.create()
    val daoInterfase = PokemonAplication.database.pokemonDao()

    suspend fun getAllCharacters() = apiInterface.getCharacters()
    suspend fun getCharacterById(id: String) = apiInterface.getCharacterById(id)

    // Database functions
    suspend fun saveAsFavorite(pokemon: PokemonEntity) = daoInterfase.addCharacter(pokemon)
    suspend fun deleteFavorite(pokemon: PokemonEntity) = daoInterfase.deleteCharacter(pokemon)
    suspend fun isFavorite(pokemon: PokemonEntity) = daoInterfase.getCharacterById(pokemon.id).isNotEmpty()
    suspend fun getFavorites() = daoInterfase.getAllCharacters()
}