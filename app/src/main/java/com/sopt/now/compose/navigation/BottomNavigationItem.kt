package com.sopt.now.compose.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.sopt.now.compose.R

enum class BottomNavigationItem(
    val title: Int, val icon: ImageVector, val route: String
) {
    Home(
        R.string.nav_home,
        Icons.Filled.Home,
        "HOME"
    ),
    Search(
        R.string.nav_search,
        Icons.Filled.Search,
        "SCREEN"
    ),
    MyPage(
        R.string.nav_my_page,
        Icons.Filled.Person,
        "MY_PAGE"
    );

    companion object {
        operator fun contains(route: String) =
            entries.map { itemType -> itemType.route }.contains(route)

        fun find(route: String): BottomNavigationItem? =
            entries.find { itemType -> itemType.route == route }
    }
}