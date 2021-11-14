package com.ouvrirdeveloper.data.repositoriesImpl

import com.ouvrirdeveloper.data.datasource.TaskDataSource
import com.ouvrirdeveloper.data.safeApiCall
import com.ouvrirdeveloper.domain.models.*
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

    override fun getViewPendingTaskDetails(strSRCHDocument: String): Flow<Resource<List<PendingTaskDetail>?>> {
        return flow {
            val response = safeApiCall {
                taskDataSource.getViewPendingTaskDetails(strSRCHDocument).data?.toDomainModel()
            }
            emit(response)
        }.flowOn(Dispatchers.IO)
    }

    override fun getmaterialRequestStages(): Flow<Resource<List<MaterialRequestStage>?>> {
        return flow {
            val response = safeApiCall {
                taskDataSource.getmaterialRequestStages().data?.toDomainModel()
            }
            emit(response)
        }.flowOn(Dispatchers.IO)
    }


    override fun getPurchaseOrderStage(): Flow<Resource<List<PurchaseOrderStage>?>> {
        return flow {
            emit(safeApiCall {
                taskDataSource.getPurchaseOrderStage().data?.toDomainModel()
            })
        }.flowOn(Dispatchers.IO)
    }

    override fun getSiteMaterialReceiptStages(): Flow<Resource<List<SiteMaterialReceiptStage>?>> {
        return flow {
            emit(safeApiCall {
                taskDataSource.getSiteMaterialReceiptStages().data?.toDomainModel()
            })
        }.flowOn(Dispatchers.IO)
    }

    override fun getSupplierInvoiceStages() = flow {
        emit(safeApiCall {
            taskDataSource.getSupplierInvoiceStages().data?.toDomainModel()
        })
    }.flowOn(Dispatchers.IO)


    override fun getviewDocDetails(
        srchDOCSRCHCODE: String,
        srchDOCNUMBER: String
    ): Flow<Resource<List<RequisitionDetail>?>> {
        return flow {
            val response = safeApiCall {
                val oo = taskDataSource.getviewDocDetails(
                    srchDOCSRCHCODE = srchDOCSRCHCODE,
                    srchDOCNUMBER = srchDOCNUMBER
                )
                val ll = oo?.data?.toDomainModel()
                ll
            }
            if (response == null) {
                emit(Resource.empty(null))
            } else
                emit(response)
        }.flowOn(Dispatchers.IO)
    }

    override fun viewPendingTaskList(loadType: Int): Flow<Resource<List<PendingTask>?>> {
        return flow {
            val response = safeApiCall {
                taskDataSource.viewPendingTaskList(loadType).data?.toDomainModel()
            }
            emit(response)
        }.flowOn(Dispatchers.IO)
    }
}