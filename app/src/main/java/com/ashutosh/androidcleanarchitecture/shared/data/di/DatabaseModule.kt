package com.ashutosh.androidcleanarchitecture.shared.data.di

import android.content.Context
import androidx.room.Room
import com.ashutosh.androidcleanarchitecture.shared.data.db.AppDatabase
import com.ashutosh.androidcleanarchitecture.utils.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext app: Context) =
        Room.databaseBuilder(app, AppDatabase::class.java, DB_NAME).build()
}