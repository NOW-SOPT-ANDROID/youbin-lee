package com.sopt.now.data.di

import com.sopt.now.data.di.qualifier.AUTH
import com.sopt.now.data.di.qualifier.OPEN
import com.sopt.now.data.service.AuthService
import com.sopt.now.data.service.FollowerService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideFollowerService(@OPEN retrofit: Retrofit): FollowerService =
        retrofit.create(FollowerService::class.java)

    @Provides
    @Singleton
    fun provideAuthService(@AUTH retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)
}