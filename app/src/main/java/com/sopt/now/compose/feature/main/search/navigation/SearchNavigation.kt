package com.sopt.now.compose.feature.main.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.now.compose.feature.main.search.SearchRoute

fun NavController.searchNavigation(navOptions: NavOptions? = null) {
    navigate(
        route = Search.ROUTE,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.searchGraph() {
    composable(route = Search.ROUTE) {
        SearchRoute()
    }
}

object Search {
    const val ROUTE = "SEARCH"
}