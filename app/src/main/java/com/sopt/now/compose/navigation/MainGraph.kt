package com.sopt.now.compose.navigation

sealed class MainGraph(val route: String) {
    data object Auth : MainGraph(route = "AUTH")
    data object Main : MainGraph(route = "MAIN")
}