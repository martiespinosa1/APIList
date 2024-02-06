package com.example.apilist.api

import com.example.apilist.api.ImagesX
import com.example.apilist.api.Legalities

data class Set(
    val id: String,
    val images: ImagesX,
    val legalities: Legalities,
    val name: String,
    val printedTotal: Int,
    val ptcgoCode: String,
    val releaseDate: String,
    val series: String,
    val total: Int,
    val updatedAt: String
)