package com.example.apilist.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.apilist.model.Images
import com.example.apilist.model.Pokemon

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Detail(navController: NavController, myViewModel: APIViewModel) {
    val poke: Pokemon by myViewModel.pokemon.observeAsState(Pokemon(Data(0, "", emptyList(), "", "", "", Images("",""), "", "", "", "", emptyList(), emptyList(), emptyList(), "", emptyList())))
    myViewModel.getCharacterById()
    val fav by myViewModel.isFavorite.observeAsState(false)
    myViewModel.isFavorite(poke.data)
    val favIcon = if (fav) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder

    Scaffold(
        topBar = { MyTopAppBarDetail(navController) },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = paddingValues.calculateTopPadding())
                    .background(Color.DarkGray)
            ) {
                Card(
                    border = BorderStroke(3.dp, Color.LightGray),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.padding(8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            //.padding(paddingValues)
                            .background(Color.Gray),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    )
                    {
                        GlideImage(
                            model = poke.data.images.large,
                            contentDescription = "Card Image",
                            contentScale = ContentScale.FillHeight,
                            modifier = Modifier
                                .size(360.dp)
                                .padding(top = 25.dp)
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(top = 15.dp)
                        ) {
                            Text(
                                text = poke.data.name,
                                color = Color.LightGray,
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Monospace
                            )

                            IconButton(onClick = {
                                myViewModel.isFavorite(poke.data)
                                if (!fav) {
                                    myViewModel.saveAsFavorite(poke.data)
                                } else {
                                    myViewModel.deleteFavorite(poke.data)
                                }

                            }) {
                                Icon(
                                    imageVector = favIcon,
                                    contentDescription = "Heart",
                                    modifier = Modifier
                                        .size(45.dp)
                                        .padding(start = 8.dp),
                                    tint = Color.LightGray
                                )
                            }
                        }

                        Box(modifier = Modifier.padding(top = 15.dp, bottom = 25.dp, start = 25.dp, end = 25.dp)) {
                            Column(
                                verticalArrangement = Arrangement.Top,
                                horizontalAlignment = Alignment.Start
                            )
                            {
                                Text(
                                    text = poke.data.flavorText ?: "",
                                    color = Color.LightGray,
                                    fontSize = 15.sp,
                                    fontFamily = FontFamily.Monospace,
                                    modifier = Modifier.padding(bottom = 10.dp)
                                )
                                Text(
                                    text = "HP: ${poke.data.hp ?: ""}",
                                    color = Color.LightGray,
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily.Monospace,
                                    modifier = Modifier.padding(bottom = 10.dp)
                                )
                                Text(
                                    text = "Type: ${poke.data.types?.getOrNull(0) ?: ""}",
                                    color = Color.LightGray,
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily.Monospace,
                                    modifier = Modifier.padding(bottom = 10.dp)
                                )
                                Text(
                                    text = "Rarity: ${poke.data.rarity ?: ""}",
                                    color = Color.LightGray,
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily.Monospace,
                                    modifier = Modifier.padding(bottom = 10.dp)
                                )
                                if (!poke.data.evolvesFrom.isNullOrEmpty()) {
                                    Text(
                                        text = "Evolves from: ${poke.data.evolvesFrom}",
                                        color = Color.LightGray,
                                        fontSize = 18.sp,
                                        fontFamily = FontFamily.Monospace,
                                        modifier = Modifier.padding(bottom = 10.dp)
                                    )
                                }
                                if (!poke.data.evolvesTo.isNullOrEmpty()) {
                                    Text(
                                        text = "Evolves to: ${poke.data.evolvesTo!![0]}",
                                        color = Color.LightGray,
                                        fontSize = 18.sp,
                                        fontFamily = FontFamily.Monospace,
                                        modifier = Modifier.padding(bottom = 10.dp)
                                    )
                                }
                            }
                        }
                    }
                }
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
            titleContentColor = Color.LightGray,
            navigationIconContentColor = Color.LightGray,
            actionIconContentColor = Color.LightGray
        ),
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) { // Así se mantiene el lugar del scroll donde se encontraba y no al inicio cada vez.
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        }
    )
}

