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

    // para que si entro a la detail screen de un pokemon desde la pantalla favs que al volver atras vaya a la pantalla favs y no a list
    private val _lastScreen = MutableLiveData("list")
    val lastScreen = _lastScreen



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
    fun MyTopAppBarDetail(navController: NavController) {
        TopAppBar(
            title = { Text(text = "Detail screen", fontFamily = FontFamily.Monospace) },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.DarkGray,
                titleContentColor = Color.White,
                navigationIconContentColor = Color.White,
                actionIconContentColor = Color.White
            ),
            navigationIcon = {
                IconButton(onClick = {
                    if (_lastScreen.value == "list") navController.navigate(Routes.List.route)
                    else navController.navigate(Routes.Favs.route)
                }) {
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



    sealed class BottomNavigationScreens(val route: String, val icon: ImageVector, val label: String) {
        object Home:BottomNavigationScreens(Routes.List.route, Icons.Filled.Home, "Home")
        object Favorite:BottomNavigationScreens(Routes.List.route, Icons.Filled.Favorite, "Favorite")
    }

    val bottomNavigationItems = listOf(
        BottomNavigationScreens.Home,
        BottomNavigationScreens.Favorite
    )



    @Composable
    fun MyBottomBar(navController: NavController, bottomNavigationItems: List<BottomNavigationScreens>) {
        BottomNavigation(
            backgroundColor = Color.DarkGray,
            contentColor = Color.White
        ) {
            BottomNavigationItem(
                icon = { Icon(Icons.Filled.Home, contentDescription = "Home", tint = if (navController.currentDestination?.route == Routes.List.route) Color.Green else Color.White) },
                //label = { Text(text ="Home") },
                selected = true,
                onClick = {
                    _lastScreen.value = "list"
                    navController.navigate(Routes.List.route)
                },
                selectedContentColor = Color.Green,
                unselectedContentColor = Color.White
            )
            BottomNavigationItem(
                icon = { Icon(Icons.Filled.Favorite, contentDescription = "Favs", tint = if (navController.currentDestination?.route == Routes.Favs.route) Color.Red else Color.White) },
                //label = { Text("Favourites") },
                selected = true,
                onClick = {
                    _lastScreen.value = "favs"
                    navController.navigate(Routes.Favs.route)
                },
                selectedContentColor = Color.Red,
                unselectedContentColor = Color.White
            )
        }
    }


}