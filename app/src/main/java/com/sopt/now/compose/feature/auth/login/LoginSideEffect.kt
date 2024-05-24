package com.sopt.now.compose.feature.auth.login

sealed class LoginSideEffect {
    data object SignUpNavigation : LoginSideEffect()
    class Success(val memberId: String? = null) : LoginSideEffect()
    data class ErrorToast(val message: String) : LoginSideEffect()
    data object Failure : LoginSideEffect()
}
