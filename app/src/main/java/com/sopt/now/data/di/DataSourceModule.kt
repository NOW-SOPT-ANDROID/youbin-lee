package com.sopt.now.data.di

import com.sopt.now.data.datasource.AuthDataSource
import com.sopt.now.data.datasourceimpl.AuthDataSourceImpl
import com.sopt.now.data.repository.AuthRepositoryImpl
import com.sopt.now.domain.repository.AuthRepository
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
    abstract fun bindAuthDataSource(authDataSourceImpl: AuthDataSourceImpl): AuthDataSource

    @Binds
    @Singleton
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

}