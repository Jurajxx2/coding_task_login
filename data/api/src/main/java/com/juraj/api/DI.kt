package com.juraj.api

import com.juraj.api.impl.UserServiceImpl
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
    abstract fun provideUserService(userServiceImpl: UserServiceImpl) : UserService
}