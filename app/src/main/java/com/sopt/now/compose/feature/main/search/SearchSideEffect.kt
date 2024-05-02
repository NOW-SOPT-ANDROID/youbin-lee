package com.sopt.now.compose.feature.main.search

import com.sopt.now.compose.data.dto.response.FollowerResponseDto

sealed class SearchSideEffect {
    data class Success(val followerList: List<FollowerResponseDto.FollowerData>) :
        SearchSideEffect()

    data object Failure : SearchSideEffect()
}