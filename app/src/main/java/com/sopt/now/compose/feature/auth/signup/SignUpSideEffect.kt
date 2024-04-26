package com.sopt.now.compose.feature.auth.signup

sealed class SignUpSideEffect {
    data object LoginNavigation : SignUpSideEffect()
}