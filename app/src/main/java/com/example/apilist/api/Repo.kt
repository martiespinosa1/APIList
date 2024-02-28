package com.example.apilist.api

import com.example.apilist.model.Data
import com.example.apilist.model.PokemonAplication

class Repository {

    val apiInterface = APIInterface.create()
    val daoInterfase = PokemonAplication.database.pokemonDao()

    suspend fun getAllCharacters() = apiInterface.getCharacters()
    suspend fun getCharacterById(id: String) = apiInterface.getCharacterById(id)

    // Database functions
    suspend fun saveAsFavorite(pokemon: Data) = daoInterfase.addCharacter(pokemon)
    suspend fun deleteFavorite(pokemon: Data) = daoInterfase.deleteCharacter(pokemon)
    suspend fun isFavorite(pokemon: Data) = daoInterfase.getCharacterById(pokemon.id).isNotEmpty()
    suspend fun getFavorites() = daoInterfase.getAllCharacters()


    suspend fun getFilteredCharacters(text:String) = apiInterface.getSearchedCards(text)


}