package com.ouvrirdeveloper.domain.repositories

import com.ouvrirdeveloper.domain.models.PendingTask
import com.ouvrirdeveloper.domain.models.RemoteConfigData
import com.ouvrirdeveloper.domain.models.Resource
import com.ouvrirdeveloper.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun login(loginDetails: User): Flow<Resource<User?>>
    fun logOut()
    fun isKeepMeLoggedIn(): Boolean
    suspend fun updateDbwithPendingTasks(data: List<PendingTask>)
    suspend fun getUserFromDisk(): User?
    fun saveToDisk(userId: String, password: String, companyId: Int)
    fun updateConfig(remoteConfigData: RemoteConfigData)
    fun getremoteConfig(): Flow<RemoteConfigData>
}