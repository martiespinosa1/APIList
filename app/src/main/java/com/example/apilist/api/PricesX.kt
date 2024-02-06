package com.example.apilist.api

data class PricesX(
    val `1stEditionHolofoil`: StEditionHolofoil,
    val holofoil: Holofoil,
    val normal: Normal,
    val reverseHolofoil: ReverseHolofoil,
    val unlimitedHolofoil: UnlimitedHolofoil
)