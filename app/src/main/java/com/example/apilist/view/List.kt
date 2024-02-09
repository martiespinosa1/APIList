package com.example.apilist.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.apilist.MainActivity
import com.example.apilist.ViewModel
import com.example.apilist.model.Data
import com.example.apilist.model.PokemonList
import com.example.apilist.navigation.Routes

@Composable
fun List(navController: NavController, myViewModel: ViewModel) {
   MyRecyclerView(myViewModel = myViewModel, navController = navController)
}


@Composable
fun MyRecyclerView(myViewModel: ViewModel, navController: NavController) {
    val showLoading: Boolean by myViewModel.loading.observeAsState(true)
    val cards: PokemonList by myViewModel.characters.observeAsState(PokemonList(0, emptyList(), 0, 0, 0))
    myViewModel.getCharacters()
    if(showLoading){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.width(64.dp),
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
    else{
        LazyColumn() {
            items(cards.data) { card ->
                CharacterItem(character = card, navController = navController) }
        }
    }
}


@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CharacterItem(character: Data, navController: NavController) {
    Card(
        onClick = { navController.navigate(Routes.Detail.route) },
        border = BorderStroke(2.dp, Color.LightGray),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.padding(8.dp),
    ) {
        Row(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()) {
            GlideImage(
                model = character.images.small,
                contentDescription = "Card Image",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier.size(125.dp)
            )
            Text(
                text = character.name,
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            )
        }
    }
}
