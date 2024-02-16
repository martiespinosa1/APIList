package com.example.apilist

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apilist.api.Repository
import com.example.apilist.model.Pokemon
import com.example.apilist.model.PokemonList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewModel: ViewModel() {

    var id : String = ""

    var isFavorite by mutableStateOf(false)

    private val repository = Repository()
    private val _loading = MutableLiveData(true)
    val loading = _loading
    private val _characters = MutableLiveData<PokemonList>()
    val characters = _characters
    
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

}