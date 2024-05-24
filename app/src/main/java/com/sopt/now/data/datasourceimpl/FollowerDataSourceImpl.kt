package com.sopt.now.data.datasourceimpl

import com.sopt.now.data.datasource.FollowerDataSource
import com.sopt.now.data.dto.response.FollowerResponseDto
import com.sopt.now.data.service.FollowerService
import javax.inject.Inject

class FollowerDataSourceImpl @Inject constructor(
    private val followerService: FollowerService
) : FollowerDataSource {
    override suspend fun getFollower(page: Int): FollowerResponseDto =
        followerService.getFollowerListFromServer(page)
}