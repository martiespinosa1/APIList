package com.example.apilist.model

data class PokemonList(
    val count: Int,
    val `data`: List<Data>,
    val page: Int,
    val pageSize: Int,
    val totalCount: Int
)