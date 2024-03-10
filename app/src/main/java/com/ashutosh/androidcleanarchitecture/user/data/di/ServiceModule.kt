package com.ashutosh.androidcleanarchitecture.user.data.di

import com.ashutosh.androidcleanarchitecture.shared.data.db.AppDatabase
import com.ashutosh.androidcleanarchitecture.user.data.api.UserService
import com.ashutosh.androidcleanarchitecture.user.data.db.UserDao
import com.ashutosh.androidcleanarchitecture.user.data.mediator.UserRemoteMediator
import com.ashutosh.androidcleanarchitecture.user.data.repository.UserRepositoryAPI
import com.ashutosh.androidcleanarchitecture.user.domain.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ServiceModule {
    @Singleton
    @Provides
    fun providesUserService(retrofitBuilder: Retrofit.Builder, okHttpClient: OkHttpClient): UserService {
        return retrofitBuilder.client(okHttpClient).build().create(UserService::class.java)
    }
    @Provides
    fun providesUserRepositoryAPI(appDatabase: AppDatabase, userService: UserService): UserRepositoryAPI = UserRepositoryAPI(appDatabase, userService)
    @Provides
    fun providesUserRepository(userRepositoryAPI: UserRepositoryAPI): UserRepository = userRepositoryAPI
}