package com.example.apilist.api

import com.example.apilist.ViewModel

class Repository {

    val apiInterface = APIInterface.create()


    suspend fun getAllCharacters() = apiInterface.getCharacters()
    suspend fun getCharacterById(id: String) = apiInterface.getCharacterById(id)
}