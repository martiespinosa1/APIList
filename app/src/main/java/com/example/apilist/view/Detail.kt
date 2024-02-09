package com.example.apilist.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.apilist.ViewModel

@Composable
fun Detail(navController: NavController, myViewModel: ViewModel) {
    Text(
        text = "Detail screen",
        fontSize = 23.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Monospace
    )
}