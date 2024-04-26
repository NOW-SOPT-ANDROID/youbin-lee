package com.sopt.now.compose.feature.main.mypage

data class MyPageState(
    val id: String = "",
    val pw: String = "",
    val nickname: String = "",
    val mbti: String = "",
    val message: String? = null
)