package com.ouvrirdeveloper.data.repositoriesImpl

import com.ouvrirdeveloper.data.datasource.TaskDataSource
import com.ouvrirdeveloper.data.safeApiCall
import com.ouvrirdeveloper.domain.models.PendingTask
import com.ouvrirdeveloper.domain.models.PendingTaskDetail
import com.ouvrirdeveloper.domain.models.RequisitionDetail
import com.ouvrirdeveloper.domain.models.Resource
import com.ouvrirdeveloper.domain.repositories.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TaskRepositoryImpl(
    val taskDataSource: TaskDataSource
) : TaskRepository {
    override fun getPendingTasksDb(): Flow<List<PendingTask>> {
        return flow {
            taskDataSource.getPendingTasksDb().collect {
                emit(it.map {
                    PendingTask(
                        id = it.id ?: 0L,
                        docsrchcode = it.docsrchcode,
                        doctype = it.doctype,
                        totalcount = it.totalcount
                    )
                })
            }
        }
    }

    override fun getViewPendingTaskDetails(strSRCHDocument: String): Flow<Resource<List<PendingTaskDetail>>> {
        return flow {
            val response = safeApiCall {
                taskDataSource.getViewPendingTaskDetails(strSRCHDocument).data.toDomainModel()
            }
            emit(response)
        }.flowOn(Dispatchers.IO)
    }

    override fun getviewDocDetails(
        srchDOCSRCHCODE: String,
        srchDOCNUMBER: String
    ): Flow<Resource<List<RequisitionDetail>>> {
        return flow {
            val response = safeApiCall {
                taskDataSource.getviewDocDetails(
                    srchDOCSRCHCODE = srchDOCSRCHCODE,
                    srchDOCNUMBER = srchDOCNUMBER
                ).data.toDomainModel()
            }
            emit(response)
        }.flowOn(Dispatchers.IO)
    }
}