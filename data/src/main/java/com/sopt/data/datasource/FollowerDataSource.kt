package com.sopt.data.datasource

import com.sopt.data.dto.response.FollowerResponseDto

interface FollowerDataSource {
    suspend fun getFollower(page: Int): FollowerResponseDto
}