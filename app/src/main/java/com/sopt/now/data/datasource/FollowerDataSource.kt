package com.sopt.now.data.datasource

import com.sopt.now.data.dto.response.FollowerResponseDto

interface FollowerDataSource {
    suspend fun getFollower(page: Int): FollowerResponseDto
}