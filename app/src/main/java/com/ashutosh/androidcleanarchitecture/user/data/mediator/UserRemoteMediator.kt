package com.ashutosh.androidcleanarchitecture.user.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.ashutosh.androidcleanarchitecture.shared.data.db.AppDatabase
import com.ashutosh.androidcleanarchitecture.shared.data.model.APIResponse
import com.ashutosh.androidcleanarchitecture.user.data.api.UserService
import com.ashutosh.androidcleanarchitecture.user.data.model.UserEntity
import com.ashutosh.androidcleanarchitecture.user.data.model.UserRemoteKeys
import com.ashutosh.androidcleanarchitecture.utils.PAGE_START_INDEX
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class UserRemoteMediator @Inject constructor(
    private val getUsers: suspend (page:Int) -> APIResponse<List<UserEntity>>,
    private val appDatabase: AppDatabase
) : RemoteMediator<Int, UserEntity>() {

    private val userDao = appDatabase.userDao()
    private val userRemoteKeysDao = appDatabase.userRemoteKeysDao()
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserEntity>
    ): MediatorResult {
        return try {

            val currentPage = when(loadType){
                LoadType.APPEND ->{
                    val remoteKeys = getRemoteKeysForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    nextPage
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeysForFirstItem(state)
                    val prevPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    prevPage
                }
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    val page = remoteKeys?.nextPage?.let { it-1 } ?: PAGE_START_INDEX
                    page
                }
            }

            val response = getUsers(currentPage)

            val endOfPaginationReached = response.total_pages == currentPage

            val prevPage = if(currentPage == 1) null else currentPage-1
            val nextPage = if (endOfPaginationReached) null else currentPage+1

            appDatabase.withTransaction {
                if(loadType == LoadType.REFRESH){
                    userDao.nukeTable()
                    userRemoteKeysDao.nukeTable()
                }
                userDao.insertUser(response.data)
                val userKeys = response.data.map {
                    UserRemoteKeys(id = it.id,prevPage = prevPage, nextPage = nextPage)
                }
                userRemoteKeysDao.addKeys(userKeys)
            }

            MediatorResult.Success(endOfPaginationReached)

        }catch (e:Exception){
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeysForLastItem(state: PagingState<Int, UserEntity>):UserRemoteKeys?{
        return  state.pages.lastOrNull{ it.data.isNotEmpty() }?.data?.lastOrNull()?.let { userEntity ->
            userRemoteKeysDao.getById(userEntity.id)
        }
    }

    private suspend fun getRemoteKeysForFirstItem(state: PagingState<Int, UserEntity>):UserRemoteKeys?{
        return  state.pages.firstOrNull(){ it.data.isNotEmpty() }?.data?.firstOrNull()?.let { userEntity ->
            userRemoteKeysDao.getById(userEntity.id)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, UserEntity>):UserRemoteKeys?{
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id->
                userRemoteKeysDao.getById(id)
            }
        }
    }
}