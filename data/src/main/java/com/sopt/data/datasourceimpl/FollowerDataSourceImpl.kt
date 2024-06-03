package com.sopt.data.datasourceimpl

import com.sopt.data.datasource.FollowerDataSource
import com.sopt.data.dto.response.FollowerResponseDto
import com.sopt.data.service.FollowerService
import javax.inject.Inject

class FollowerDataSourceImpl @Inject constructor(
    private val followerService: FollowerService
) : FollowerDataSource {
    override suspend fun getFollower(page: Int): FollowerResponseDto =
        followerService.getFollowerListFromServer(page)
}