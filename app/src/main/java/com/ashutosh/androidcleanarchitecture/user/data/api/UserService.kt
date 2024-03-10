package com.ashutosh.androidcleanarchitecture.user.data.api

import com.ashutosh.androidcleanarchitecture.shared.data.model.APIResponse
import com.ashutosh.androidcleanarchitecture.user.data.model.UserEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("/api/users")
    suspend fun getUsers(@Query("page") page:Int): APIResponse<List<UserEntity>>

}