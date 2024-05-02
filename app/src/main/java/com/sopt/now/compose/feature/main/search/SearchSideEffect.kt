package com.sopt.now.compose.feature.main.search

sealed class SearchSideEffect {
    data class Success(val followerList: List<FollowerInfo>) : SearchSideEffect()
    data object Failure : SearchSideEffect()
}