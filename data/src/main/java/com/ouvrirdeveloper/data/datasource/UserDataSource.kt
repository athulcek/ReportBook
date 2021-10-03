package com.ouvrirdeveloper.data.datasource

import com.bushnell.golf.data.database.dao.PendingTaskDao
import com.ouvrirdeveloper.core.model.BaseResponse
import com.ouvrirdeveloper.data.api.UserApiService
import com.ouvrirdeveloper.data.models.entities.PendingTaskEntity
import com.ouvrirdeveloper.data.models.responses.LoginResponse
import com.ouvrirdeveloper.data.models.responses.PedingTaskListResponse
import com.ouvrirdeveloper.domain.models.User

class UserDataSource(
    private val apiService: UserApiService,
    private val pendingTaskDao: PendingTaskDao
) {
    suspend fun login(loginDetails: User): BaseResponse<LoginResponse> {
        return apiService.login(loginDetails.userId, loginDetails.password)
    }

    suspend fun viewPendingTaskList(loadType: Int): BaseResponse<PedingTaskListResponse> {
        return apiService.viewPendingTaskList(loadType)
    }


    suspend fun updateDbwithPendingTasks(data: List<PendingTaskEntity>) {
        pendingTaskDao.insertList(data)
    }

}