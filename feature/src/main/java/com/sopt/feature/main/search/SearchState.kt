package com.sopt.feature.main.search

import com.sopt.data.dto.response.FollowerResponseDto

sealed class SearchState {
    data object Empty : SearchState()
    data object Loading : SearchState()
    data class Success(val followerList: List<FollowerResponseDto.FollowerData>) : SearchState()
}