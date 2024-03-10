package com.ashutosh.androidcleanarchitecture.user.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    val avatar: String,
    val email: String,
    val first_name: String,
    @PrimaryKey
    val id: Int,
    val last_name: String
)