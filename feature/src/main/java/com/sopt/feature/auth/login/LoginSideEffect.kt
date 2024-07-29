package com.sopt.feature.auth.login

sealed class LoginSideEffect {
    object SignUpNavigation : LoginSideEffect()
    class Success(val memberId: String? = null) : LoginSideEffect()
    data class Failure(val message: String) : LoginSideEffect()
}
