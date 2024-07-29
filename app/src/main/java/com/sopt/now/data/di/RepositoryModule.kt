package com.sopt.now.data.di

import com.sopt.now.data.repository.AuthRepositoryImpl
import com.sopt.now.data.repository.FollowerRepositoryImpl
import com.sopt.now.domain.repository.AuthRepository
import com.sopt.now.domain.repository.FollowerRepository
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