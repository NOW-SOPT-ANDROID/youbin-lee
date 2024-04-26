package com.sopt.now.compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.sopt.now.compose.feature.auth.login.navigation.loginNavigation
import com.sopt.now.compose.feature.auth.signup.navigation.SignUp
import com.sopt.now.compose.feature.auth.signup.navigation.signUpNavigation
import com.sopt.now.compose.feature.main.home.navigation.homeNavigation
import com.sopt.now.compose.feature.main.mypage.navigation.myPageNavigation
import com.sopt.now.compose.feature.main.search.navigation.searchNavigation

class MainNavigator(
    val navHostController: NavHostController
) {
    private val currentRoute: NavDestination?
        @Composable get() = navHostController.currentBackStackEntryAsState().value?.destination

    val currentNavItem: BottomNavigationItem?
        @Composable get() = currentRoute?.route?.let(BottomNavigationItem::find)

    val startDestination = SignUp.ROUTE
    fun navigateMainNavigation(itemType: BottomNavigationItem) {
        navOptions {
            launchSingleTop = true
            restoreState = true
        }.let {
            when (itemType) {
                BottomNavigationItem.Home -> navHostController.homeNavigation()
                BottomNavigationItem.MyPage -> navHostController.myPageNavigation()
                BottomNavigationItem.Search -> navHostController.searchNavigation()
            }
        }
    }

    fun loginNavigation() {
        navHostController.loginNavigation()
    }

    fun signUpNavigation() {
        navHostController.signUpNavigation()
    }

    @Composable
    fun showBottomBar(): Boolean =
        currentRoute?.route?.let { currentRoute -> currentRoute in BottomNavigationItem }
            ?: false
}

@Composable
fun rememberMainNavigator(
    navHostController: NavHostController = rememberNavController()
): MainNavigator = remember(navHostController) {
    MainNavigator(navHostController = navHostController)
}