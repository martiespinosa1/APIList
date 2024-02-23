package com.example.apilist

import android.util.Log
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.apilist.api.Repository
import com.example.apilist.model.Data
import com.example.apilist.model.Pokemon
import com.example.apilist.model.PokemonList
import com.example.apilist.navigation.Routes
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
    private val _favorites = MutableLiveData<MutableList<Data>>()
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

    fun isFavorite(pokemon: Data) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.isFavorite(pokemon)
            withContext(Dispatchers.Main) {
                _isFavorite.value = response
            }
        }
    }

    fun saveAsFavorite(pokemon: Data) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.saveAsFavorite(pokemon)
            _isFavorite.postValue(true)
        }
    }

    fun deleteFavorite(pokemon: Data) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteFavorite(pokemon)
            _isFavorite.postValue(false)
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MyTopAppBar1(navController: NavController) {
        TopAppBar(
            title = { Text(text = "Home screen", fontFamily = FontFamily.Monospace) },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.DarkGray,
                titleContentColor = Color.White,
                navigationIconContentColor = Color.White,
                actionIconContentColor = Color.White
            ),
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Menu")
                }
            }
        )
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MyTopAppBar2(navController: NavController) {
        TopAppBar(
            title = { Text(text = "Detail screen", fontFamily = FontFamily.Monospace) },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.DarkGray,
                titleContentColor = Color.White,
                navigationIconContentColor = Color.White,
                actionIconContentColor = Color.White
            ),
            navigationIcon = {
                IconButton(onClick = { navController.navigate(Routes.List.route) }) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        )
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MyTopAppBarFavs(navController: NavController) {
        TopAppBar(
            title = { Text(text = "Favs screen", fontFamily = FontFamily.Monospace) },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.DarkGray,
                titleContentColor = Color.White,
                navigationIconContentColor = Color.White,
                actionIconContentColor = Color.White
            ),
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Menu")
                }
            }
        )
    }




}