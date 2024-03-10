package com.ashutosh.androidcleanarchitecture.user.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ashutosh.androidcleanarchitecture.user.data.model.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(list: List<UserEntity>)
    @Query("SELECT * FROM user")
    fun getUser(): PagingSource<Int, UserEntity>
    @Query("DELETE FROM user")
    suspend fun nukeTable(): Int

}