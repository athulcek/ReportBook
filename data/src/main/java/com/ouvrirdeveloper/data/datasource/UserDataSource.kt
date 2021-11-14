package com.ouvrirdeveloper.data.datasource

import com.ouvrirdeveloper.core.model.BaseResponse
import com.ouvrirdeveloper.data.api.UserApiService
import com.ouvrirdeveloper.data.database.dao.PendingTaskDao
import com.ouvrirdeveloper.data.models.entities.PendingTaskEntity
import com.ouvrirdeveloper.data.models.responses.LoginResponse
import com.ouvrirdeveloper.domain.models.User

class UserDataSource(
    private val apiService: UserApiService,
    private val pendingTaskDao: PendingTaskDao
) {
    suspend fun login(loginDetails: User): BaseResponse<LoginResponse> {
        return apiService.login(loginDetails.userId, loginDetails.password, loginDetails.companyId)
    }

    suspend fun updateDbwithPendingTasks(data: List<PendingTaskEntity>) {
        pendingTaskDao.insertList(data)
    }

}