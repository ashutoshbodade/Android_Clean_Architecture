package com.ashutosh.androidcleanarchitecture.user.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.ashutosh.androidcleanarchitecture.user.domain.User
import com.ashutosh.androidcleanarchitecture.user.data.repository.UserRepositoryAPI
import com.ashutosh.androidcleanarchitecture.user.domain.UserRepository
import com.ashutosh.androidcleanarchitecture.user.domain.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userUseCase: UserUseCase) :ViewModel(){
    fun getUserList():Flow<PagingData<User>>{
        return userUseCase.getUserPagingData()
    }
}