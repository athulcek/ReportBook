package com.ouvrirdeveloper.domain.repositories

import com.ouvrirdeveloper.domain.models.PendingTask
import com.ouvrirdeveloper.domain.models.PendingTaskDetail
import com.ouvrirdeveloper.domain.models.RequisitionDetail
import com.ouvrirdeveloper.domain.models.Resource
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getPendingTasksDb(): Flow<List<PendingTask>>
    fun getViewPendingTaskDetails(strSRCHDocument: String): Flow<Resource<List<PendingTaskDetail>>>

    fun getviewDocDetails(
        srchDOCSRCHCODE: String,
        srchDOCNUMBER: String
    ): Flow<Resource<List<RequisitionDetail>>>
}