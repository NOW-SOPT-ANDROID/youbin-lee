package com.sopt.now.compose.feature.main.search

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun SearchScreen(navController: NavController) {

}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    val navController = rememberNavController()
    SearchScreen(navController)
}