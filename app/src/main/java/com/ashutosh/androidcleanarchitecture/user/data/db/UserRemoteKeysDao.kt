package com.ashutosh.androidcleanarchitecture.user.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ashutosh.androidcleanarchitecture.user.data.model.UserRemoteKeys


@Dao
interface UserRemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addKeys(remoteKey: List<UserRemoteKeys>)
    @Query("SELECT * FROM user_remote_keys WHERE id = :id")
    suspend fun getById(id: Int): UserRemoteKeys?
    @Query("DELETE FROM user_remote_keys")
    suspend fun nukeTable()

}