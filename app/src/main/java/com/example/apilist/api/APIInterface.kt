package com.example.apilist.api

import com.example.apilist.model.Pokemon
import com.example.apilist.model.PokemonList
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIInterface {

    @GET("cards")
    suspend fun getCharacters(): Response<PokemonList>

    @GET("cards/{id}")
    suspend fun getCharacterById(@Path("id") id: String): Response<Pokemon>

    @GET("cards?q")
    suspend fun getSearchedCards(@Query("q") q: String) : Response<PokemonList>


    companion object {
        val BASE_URL = "https://api.pokemontcg.io/v2/"
        fun create(): APIInterface {
            val client = OkHttpClient.Builder().build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(APIInterface::class.java)
        }
    }

}