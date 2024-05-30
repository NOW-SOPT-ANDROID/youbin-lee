package com.sopt.now.data.di

import com.sopt.now.data.datasource.AuthDataSource
import com.sopt.now.data.datasource.FollowerDataSource
import com.sopt.now.data.datasourceimpl.AuthDataSourceImpl
import com.sopt.now.data.datasourceimpl.FollowerDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideFollowerDataSource(followerDataSourceImpl: FollowerDataSourceImpl): FollowerDataSource =
        followerDataSourceImpl

    @Provides
    @Singleton
    fun provideAuthDataSource(authDataSourceImpl: AuthDataSourceImpl): AuthDataSource =
        authDataSourceImpl
}
