package com.sopt.now.compose.di

import com.sopt.data.datasource.AuthDataSource
import com.sopt.data.datasource.FollowerDataSource
import com.sopt.data.datasourceimpl.AuthDataSourceImpl
import com.sopt.data.datasourceimpl.FollowerDataSourceImpl
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
    abstract fun bindAuthDataSource(authDataSourceImpl: AuthDataSourceImpl): AuthDataSource

}