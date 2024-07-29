package com.sopt.data.repositoryimpl

import com.sopt.data.datasource.FollowerDataSource
import com.sopt.domain.entity.response.FollowerResponseModel
import com.sopt.domain.repository.FollowerRepository
import javax.inject.Inject

class FollowerRepositoryImpl @Inject constructor(private val followerDataSource: FollowerDataSource)
    : FollowerRepository {
    override suspend fun getFollowerList(page: Int) : Result<List<FollowerResponseModel>> =
        runCatching {
            followerDataSource.getFollower(page).toFollowerEntity()
        }
}