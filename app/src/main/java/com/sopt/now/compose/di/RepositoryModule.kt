package com.sopt.now.compose.di

import com.sopt.data.repositoryimpl.AuthRepositoryImpl
import com.sopt.data.repositoryimpl.FollowerRepositoryImpl
import com.sopt.domain.repository.AuthRepository
import com.sopt.domain.repository.FollowerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideFollowerRepository(followerRepositoryImpl: FollowerRepositoryImpl): FollowerRepository =
        followerRepositoryImpl

    @Provides
    @Singleton
    fun provideAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository =
        authRepositoryImpl
}