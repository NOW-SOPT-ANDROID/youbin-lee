package com.sopt.now.compose.feature.auth.login

sealed class LoginSideEffect {
    data object SignUpNavigation : LoginSideEffect()
    class Success(val memberId: String? = null) : LoginSideEffect()
    data object InputError : LoginSideEffect()
    data object Failure : LoginSideEffect()
}
