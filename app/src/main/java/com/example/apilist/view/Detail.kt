package com.example.apilist.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.apilist.ViewModel
import com.example.apilist.model.Data
import com.example.apilist.navigation.Routes

@Composable
fun Detail(navController: NavController, myViewModel: ViewModel) {
    Text(
        text = myViewModel.id,
        fontSize = 23.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Monospace
    )

    myViewModel.getCharactersById()
    CharacterDetail(pokemon = myViewModel.pokemon)


}


@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetail(pokemon: Data) {

    Card(border = BorderStroke(2.dp, Color.LightGray),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        Row(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()) {
            GlideImage(
                model = pokemon.images.large,
                contentDescription = "Card Image",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier.size(125.dp)
            )
            Text(
                text = pokemon.name,
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            )
        }
    }


}