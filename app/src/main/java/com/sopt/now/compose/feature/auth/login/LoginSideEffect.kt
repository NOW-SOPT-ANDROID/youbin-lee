package com.sopt.now.compose.feature.auth.login

sealed class LoginSideEffect {
    data object SignUpNavigation : LoginSideEffect()
    data object MainNavigation : LoginSideEffect()
}
