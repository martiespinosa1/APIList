package com.example.apilist.api

import com.example.apilist.api.Data

data class PokemonList(
    val count: Int,
    val `data`: List<Data>,
    val page: Int,
    val pageSize: Int,
    val totalCount: Int
)