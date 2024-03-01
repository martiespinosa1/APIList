package com.example.apilist.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.apilist.viewmodel.APIViewModel
import com.example.apilist.model.Data
import com.example.apilist.model.PokemonList
import com.example.apilist.navigation.Routes

@Composable
fun List(navController: NavController, myViewModel: APIViewModel) {
    val searchText: String by myViewModel.searchText.observeAsState("")
    MyRecyclerView(myViewModel = myViewModel, navController = navController, searchText = searchText)
}


@Composable
fun MyRecyclerView(myViewModel: APIViewModel, navController: NavController, searchText: String) {
    val showLoading: Boolean by myViewModel.loading.observeAsState(true)
    val cards: PokemonList by myViewModel.characters.observeAsState(PokemonList(0, emptyList(), 0, 0, 0))

    myViewModel.getCharacters()

    if(showLoading){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.fillMaxWidth(0.2f),
                color = Color.LightGray
            )
        }
    }
    else{
        Scaffold(
            topBar = { MyTopAppBar(myViewModel) },
            bottomBar = { MyBottomBar(myViewModel, navController, bottomNavigationItems) },
            content = { paddingValues ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .background(Color.DarkGray)
                ) {
                    val filteredList = cards.data.filter { it.name.contains(searchText, ignoreCase = true) }
                    LazyColumn() {
                        items(filteredList) { card ->
                            CharacterItem(character = card, navController = navController, myAPIViewModel = myViewModel)
                        }
                    }
                }
            }
        )
    }
}


@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CharacterItem(character: Data, navController: NavController, myAPIViewModel: APIViewModel) {
    Card(
        onClick = {
            myAPIViewModel.id = character.id
            navController.navigate(Routes.Detail.route)
        },
        border = BorderStroke(3.dp, Color.LightGray),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        Box(
            modifier = Modifier.background(Color.Gray).fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 16.dp, end = 16.dp, bottom = 16.dp)
            ) {
                GlideImage(
                    model = character.images.small,
                    contentDescription = "Card Image",
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier.size(125.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = character.name,
                    color = Color.LightGray,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace
                )
            }
        }
    }
}



var showSearchBar by mutableStateOf(false)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(myViewModel: APIViewModel) {
    TopAppBar(
        title = { Text(text = "Home screen", fontFamily = FontFamily.Monospace) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.DarkGray,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White,
            actionIconContentColor = Color.White
        ),
        actions = {
            if (showSearchBar) MySearchBar(myViewModel = myViewModel)

            IconButton(onClick = { showSearchBar = !showSearchBar }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
            }
        }
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySearchBar (myViewModel: APIViewModel) {
    val searchText by myViewModel.searchText.observeAsState("")
    SearchBar(
        colors = SearchBarDefaults.colors(Color.DarkGray, inputFieldColors = TextFieldDefaults.colors(focusedTextColor = Color.LightGray, unfocusedTextColor = Color.LightGray)
        ),
        query = searchText,
        onQueryChange = { myViewModel.onSearchTextChange(it) },
        onSearch = { myViewModel.onSearchTextChange(it) },
        active = true,
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "Close",
                modifier = Modifier.clickable {
                    showSearchBar = !showSearchBar
                    myViewModel.onSearchTextChange("")
                },
                tint = Color.White
            )
        },
        placeholder = { Text("Search Pokemon...", fontFamily = FontFamily.Monospace, fontSize = 18.sp, color = Color.LightGray) },
        onActiveChange = {},
        modifier = Modifier
            .fillMaxHeight(0.1f)
            .clip(CircleShape)
    ) {}
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
fun MyBottomBar(myViewModel: APIViewModel, navController: NavController, bottomNavigationItems: List<BottomNavigationScreens>) {
    BottomNavigation(
        backgroundColor = Color.DarkGray,
        contentColor = Color.White
    ) {
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home", tint = if (navController.currentDestination?.route == Routes.List.route) Color.Green.copy(alpha = 0.6f) else Color.White) },
            //label = { Text(text ="Home") },
            selected = true,
            onClick = {
                navController.navigate(Routes.List.route)
            },
            selectedContentColor = Color.Green,
            unselectedContentColor = Color.White
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Favorite, contentDescription = "Favs", tint = if (navController.currentDestination?.route == Routes.Favs.route) Color.Red.copy(alpha = 0.6f) else Color.White) },
            //label = { Text("Favourites") },
            selected = true,
            onClick = {
                navController.navigate(Routes.Favs.route)
            },
            selectedContentColor = Color.Red,
            unselectedContentColor = Color.White
        )
    }
}

