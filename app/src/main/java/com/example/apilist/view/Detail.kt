package com.example.apilist.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.apilist.model.PokemonList

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Detail(navController: NavController, myViewModel: ViewModel) {
    val poke: Pokemon by myViewModel.pokemon.observeAsState(Pokemon(Data(emptyList(), emptyList(), 0, "", emptyList(), "", "", "", Images("",""), "", "", emptyList(), "", "", "", emptyList(), emptyList(), emptyList(), emptyList(), "", emptyList(), emptyList(), emptyList(), emptyList())))
    myViewModel.getCharacterById()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        GlideImage(
            model = poke.data.images.large,
            contentDescription = "Card Image",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.size(300.dp)
        )
        Text(
            text = poke.data.name,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )
        Box(modifier = Modifier.padding(top = 50.dp)) {
            Column (
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            )
            {

                Text(
                    text = "HP: ${poke.data.hp}",
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Monospace
                )
                Text(
                    text = "Rarity: ${poke.data.rarity}",
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Monospace
                )
                Text(
                    text = "Current price: ${poke.data.id}",
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Monospace
                )
            }
        }
    }
}

