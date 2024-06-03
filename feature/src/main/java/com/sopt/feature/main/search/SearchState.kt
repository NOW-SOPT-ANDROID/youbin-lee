package com.sopt.feature.main.search

import com.sopt.domain.entity.response.FollowerResponseModel

sealed class SearchState {
     object Empty : SearchState()
     object Loading : SearchState()
    data class Success(val followerList: List<FollowerResponseModel>) : SearchState()
}