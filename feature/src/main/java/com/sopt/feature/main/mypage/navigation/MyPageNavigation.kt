package com.sopt.feature.main.mypage.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.feature.main.mypage.MyPageRoute

fun NavController.myPageNavigation(navOptions: NavOptions? = null) {
    navigate(
        route = MyPage.ROUTE,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.myPageGraph(
    navHostController: NavHostController
) {
    composable(
        route = MyPage.ROUTE
    ) {
        MyPageRoute(
            navHostController = navHostController
        )
    }
}

object MyPage {
    const val ROUTE = "MY_PAGE"
}

