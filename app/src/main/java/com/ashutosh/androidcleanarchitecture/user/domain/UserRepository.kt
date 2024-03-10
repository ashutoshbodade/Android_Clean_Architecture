package com.ashutosh.androidcleanarchitecture.user.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUserList(): Flow<PagingData<User>>
}


