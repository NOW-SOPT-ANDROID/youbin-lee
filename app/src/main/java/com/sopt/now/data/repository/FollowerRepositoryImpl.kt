package com.sopt.now.data.repository

import com.sopt.now.data.datasource.FollowerDataSource
import com.sopt.now.domain.entity.response.FollowerResponseModel
import com.sopt.now.domain.repository.FollowerRepository
import javax.inject.Inject

class FollowerRepositoryImpl @Inject constructor(private val followerDataSource: FollowerDataSource)
    : FollowerRepository {
    override suspend fun getFollowerList(page: Int) : Result<List<FollowerResponseModel>> =
        runCatching {
            followerDataSource.getFollower(page).toFollowerEntity()
        }
}