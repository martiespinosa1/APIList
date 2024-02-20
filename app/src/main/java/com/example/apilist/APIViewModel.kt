package com.example.apilist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apilist.api.Repository
import com.example.apilist.model.Pokemon
import com.example.apilist.model.PokemonEntity
import com.example.apilist.model.PokemonList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class APIViewModel: ViewModel() {
    var id : String = ""

    private val repository = Repository()
    private val _loading = MutableLiveData(true)
    val loading = _loading
    private val _characters = MutableLiveData<PokemonList>()
    val characters = _characters
    private val _isFavorite = MutableLiveData(false)
    val isFavorite = _isFavorite
    private val _favorites = MutableLiveData<MutableList<PokemonEntity>>()
    val favorites = _favorites
    
    private var _pokemon = MutableLiveData<Pokemon>()
    var pokemon = _pokemon



    fun getCharacters(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getAllCharacters()
            withContext(Dispatchers.Main) {
                if(response.isSuccessful){
                    _characters.value = response.body()
                    _loading.value = false
                }
                else{
                    Log.e("Error :", response.message())
                }
            }
        }
    }

    fun getCharacterById(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getCharacterById(id)
            withContext(Dispatchers.Main) {
                if(response.isSuccessful){
                    _pokemon.value = response.body()
                    _loading.value = false
                }
                else{
                    Log.e("Error :", response.message())
                }
            }
        }
    }



    fun getFavorites() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getFavorites()
            withContext(Dispatchers.Main) {
                _favorites.value = response
                _loading.value = false
            }
        }
    }

    fun isFavorite(pokemon: PokemonEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.isFavorite(pokemon)
            withContext(Dispatchers.Main) {
                _isFavorite.value = response
            }
        }
    }

    fun saveAsFavorite(pokemon: PokemonEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.saveAsFavorite(pokemon)
        }
    }

    fun deleteFavorite(pokemon: PokemonEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteFavorite(pokemon)
        }
    }
}