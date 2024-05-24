package com.sopt.now.compose.feature.main.search

import com.sopt.now.compose.data.dto.response.FollowerResponseDto

sealed class SearchState {
    data object Empty : SearchState()
    data object Loading : SearchState()
    data class Success(val followerList: List<FollowerResponseDto.FollowerData>) : SearchState()
}