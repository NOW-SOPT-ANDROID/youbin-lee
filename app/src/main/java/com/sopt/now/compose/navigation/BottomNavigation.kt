package com.sopt.now.compose.navigation

import BottomNavItem
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun BottomNavigation(navController: NavController) {
    val currentRoute = navController.currentDestination?.route

    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Search,
        BottomNavItem.MyPage
    )

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(item.icon, contentDescription = stringResource(id = item.title))
                },
                label = { Text(stringResource(id = item.title), fontSize = 9.sp) },
                )
        }
    }
}