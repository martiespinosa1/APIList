package com.example.apilist.navigation

sealed class Routes(val route: String) {
    object Launch: Routes("Launch")
    object List: Routes("List")
}