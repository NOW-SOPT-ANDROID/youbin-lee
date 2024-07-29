package com.sopt.domain.repository

import com.sopt.domain.entity.response.FollowerResponseModel

interface FollowerRepository {
    suspend fun getFollowerList(page: Int): Result<List<FollowerResponseModel>>
}