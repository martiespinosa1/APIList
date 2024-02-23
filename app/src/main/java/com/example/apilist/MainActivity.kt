package com.example.apilist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.apilist.navigation.Routes
import com.example.apilist.ui.theme.APIListTheme
import com.example.apilist.view.LaunchAnimation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apilist.view.Detail
import com.example.apilist.view.Favs
import com.example.apilist.view.List


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val myAPIViewModel by viewModels<APIViewModel>()
        super.onCreate(savedInstanceState)
        setContent {
            APIListTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val navigationController = rememberNavController()
                    NavHost(
                        navController = navigationController,
                        startDestination = Routes.Launch.route
                    ) {
                        composable(Routes.Launch.route) { LaunchAnimation(navigationController) }
                        composable(Routes.List.route) { List(navigationController, myAPIViewModel) }
                        composable(Routes.Detail.route) { Detail(navigationController, myAPIViewModel) }
                        composable(Routes.Favs.route) { Favs(navigationController, myAPIViewModel) }
                    }

                }
            }
        }
    }
}



