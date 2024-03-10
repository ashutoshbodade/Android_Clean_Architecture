package com.ashutosh.androidcleanarchitecture.user.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserUseCase @Inject constructor(private val userRepository: UserRepository) {
    fun getUserPagingData(): Flow<PagingData<User>> = userRepository.getUserList()
}