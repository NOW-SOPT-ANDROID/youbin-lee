package com.sopt.now.data.di

import com.sopt.now.data.datasource.FollowerDataSource
import com.sopt.now.data.datasourceimpl.FollowerDataSourceImpl
import com.sopt.now.data.repository.FollowerRepositoryImpl
import com.sopt.now.domain.repository.FollowerRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindFollowerDataSource(followerDataSourceImpl: FollowerDataSourceImpl): FollowerDataSource

    @Binds
    @Singleton
    abstract fun bindFollowerRepository(followerRepositoryImpl: FollowerRepositoryImpl): FollowerRepository

}