package com.example.apilist.model

import android.app.Application
import androidx.room.Room

class PokemonAplication: Application() {
    companion object {
        lateinit var database: PokemonDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, PokemonDatabase::class.java, "PokemonDatabase").build()
    }
}