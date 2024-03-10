package com.ashutosh.androidcleanarchitecture.user.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_remote_keys")
data class UserRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?
)