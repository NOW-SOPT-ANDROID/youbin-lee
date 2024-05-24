package com.sopt.now.domain.repository

import com.sopt.now.domain.entity.response.FollowerResponseModel

interface FollowerRepository {
    suspend fun getFollowerList(page: Int): Result<List<FollowerResponseModel>>
}