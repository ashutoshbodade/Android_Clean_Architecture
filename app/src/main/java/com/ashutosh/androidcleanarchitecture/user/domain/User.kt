package com.ashutosh.androidcleanarchitecture.user.domain

data class User(
    val email: String,
    val firstName: String,
    val userId: Int,
    val lastName: String,
    val avatar: String
)