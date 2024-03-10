package com.ashutosh.androidcleanarchitecture.shared.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ashutosh.androidcleanarchitecture.user.data.model.UserEntity
import com.ashutosh.androidcleanarchitecture.user.data.db.UserDao
import com.ashutosh.androidcleanarchitecture.user.data.db.UserRemoteKeysDao
import com.ashutosh.androidcleanarchitecture.user.data.model.UserRemoteKeys

@Database(entities = [UserEntity::class, UserRemoteKeys::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
    abstract fun userRemoteKeysDao() : UserRemoteKeysDao
}
