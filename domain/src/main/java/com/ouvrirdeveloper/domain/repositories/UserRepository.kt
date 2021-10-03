package com.bushnell.golf.domain.repositories

import com.ouvrirdeveloper.domain.models.PendingTask
import com.ouvrirdeveloper.domain.models.Resource
import com.ouvrirdeveloper.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun login(loginDetails: User): Flow<Resource<User?>>
    fun viewPendingTaskList(loadType: Int): Flow<Resource<List<PendingTask>>>
    fun logOut()
    fun isKeepMeLoggedIn(): Boolean
    suspend fun updateDbwithPendingTasks(data: List<PendingTask>)
}