package com.example.apilist.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import com.example.apilist.ViewModel
import com.example.apilist.model.Data
import com.example.apilist.model.Images
import com.example.apilist.model.Pokemon
import com.example.apilist.navigation.Routes

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun Detail(navController: NavController, myViewModel: ViewModel) {
    val poke: Pokemon by myViewModel.pokemon.observeAsState(Pokemon(Data(emptyList(), emptyList(), 0, "", emptyList(), "", "", "", Images("",""), "", "", emptyList(), "", "", "", emptyList(), emptyList(), emptyList(), emptyList(), "", emptyList(), emptyList())))
    myViewModel.getCharacterById()

    Scaffold(
        topBar = { MyTopAppBar2(navController) },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                GlideImage(
                    model = poke.data.images.large,
                    contentDescription = "Card Image",
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier.size(360.dp).padding(top = 50.dp)
                )
                Text(
                    text = poke.data.name,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.padding(25.dp)
                )
                Box(modifier = Modifier.padding(top = 25.dp)) {
                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start
                    )
                    {
                        Text(
                            text = "${poke.data.flavorText}",
                            fontSize = 20.sp,
                            fontFamily = FontFamily.Monospace,
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
                        Text(
                            text = "HP: ${poke.data.hp}",
                            fontSize = 20.sp,
                            fontFamily = FontFamily.Monospace,
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
                        Text(
                            text = "Rarity: ${poke.data.rarity}",
                            fontSize = 20.sp,
                            fontFamily = FontFamily.Monospace,
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
                    }
                }
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


