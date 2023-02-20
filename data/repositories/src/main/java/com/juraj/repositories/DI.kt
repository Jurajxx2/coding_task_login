package com.juraj.repositories

import com.juraj.repositories.impl.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class DI {

    @Singleton
    @Binds
    abstract fun provideUserRepository(userRepositoryImpl: UserRepositoryImpl) : UserRepository
}