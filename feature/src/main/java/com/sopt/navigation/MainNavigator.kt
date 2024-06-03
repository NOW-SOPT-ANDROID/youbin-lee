package com.sopt.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.sopt.feature.auth.login.navigation.Login
import com.sopt.feature.auth.login.navigation.loginNavigation
import com.sopt.feature.auth.signup.navigation.signUpNavigation
import com.sopt.feature.main.home.navigation.homeNavigation
import com.sopt.feature.main.mypage.navigation.myPageNavigation
import com.sopt.feature.main.search.navigation.searchNavigation

class MainNavigator(
    val navHostController: NavHostController
) {
    private val currentRoute: NavDestination?
        @Composable get() = navHostController.currentBackStackEntryAsState().value?.destination

    val currentNavItem: BottomNavigationItem?
        @Composable get() = currentRoute?.route?.let(BottomNavigationItem::find)

    val startDestination = Login.ROUTE
    fun navigateMainNavigation(itemType: BottomNavigationItem) {
        navOptions {
            popBackStack()
            launchSingleTop = true
            restoreState = true
        }.let {
            when (itemType) {
                BottomNavigationItem.Home -> navHostController.homeNavigation(navOptions = it)
                BottomNavigationItem.Search -> navHostController.searchNavigation(navOptions = it)
                BottomNavigationItem.MyPage -> navHostController.myPageNavigation(navOptions = it)
            }
        }
    }

    fun loginNavigation() {
        navHostController.loginNavigation()
    }

    fun signUpNavigation() {
        navHostController.signUpNavigation()
    }

    fun popBackStack() {
        navHostController.popBackStack()
    }

    @Composable
    fun showBottomBar(): Boolean {
        val currentRoute = currentRoute ?: return false
        return currentRoute.route in BottomNavigationItem
    }

}

@Composable
fun rememberMainNavigator(
    navHostController: NavHostController = rememberNavController()
): MainNavigator = remember(navHostController) {
    MainNavigator(navHostController = navHostController)
}