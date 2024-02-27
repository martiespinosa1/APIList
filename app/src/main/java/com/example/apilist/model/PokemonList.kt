package com.example.apilist.model

data class PokemonList(
    val count: Int,
    var `data`: List<Data>,
    val page: Int,
    val pageSize: Int,
    val totalCount: Int
)