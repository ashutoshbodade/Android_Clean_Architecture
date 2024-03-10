package com.ashutosh.androidcleanarchitecture.user.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.ashutosh.androidcleanarchitecture.shared.data.db.AppDatabase
import com.ashutosh.androidcleanarchitecture.user.data.api.UserService
import com.ashutosh.androidcleanarchitecture.user.data.db.UserDao
import com.ashutosh.androidcleanarchitecture.user.data.mediator.UserRemoteMediator
import com.ashutosh.androidcleanarchitecture.user.domain.User
import com.ashutosh.androidcleanarchitecture.user.domain.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryAPI @Inject constructor(private val appDatabase: AppDatabase,private val userService: UserService):
    UserRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getUserList(): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(pageSize = 6),
            remoteMediator = UserRemoteMediator(userService::getUsers, appDatabase),
            pagingSourceFactory = { appDatabase.userDao().getUser() }
        ).flow.map { pagingData ->
            pagingData.map { userEntity ->
                User(userEntity.email, userEntity.first_name, userEntity.id, userEntity.last_name, userEntity.avatar)
            }
        }
    }
}