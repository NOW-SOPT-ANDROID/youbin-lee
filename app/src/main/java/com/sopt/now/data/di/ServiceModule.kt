package com.sopt.now.data.di

import com.sopt.now.data.di.qualifier.OPEN
import com.sopt.now.data.service.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//object ServiceModule {
//
//    @Provides
//    @Singleton
//    fun provideAuthService(@OPEN retrofit: Retrofit): AuthService =
//        retrofit.create(AuthService::class.java)
//}