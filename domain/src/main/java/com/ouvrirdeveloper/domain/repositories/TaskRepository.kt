package com.ouvrirdeveloper.domain.repositories

import com.ouvrirdeveloper.domain.models.*
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getPendingTasksDb(): Flow<List<PendingTask>>
    fun getViewPendingTaskDetails(strSRCHDocument: String): Flow<Resource<List<PendingTaskDetail>?>>
    fun getmaterialRequestStages(): Flow<Resource<List<MaterialRequestStage>?>>

    fun getPurchaseOrderStage(): Flow<Resource<List<PurchaseOrderStage>?>>
    fun getSiteMaterialReceiptStages(): Flow<Resource<List<SiteMaterialReceiptStage>?>>
    fun getSupplierInvoiceStages(): Flow<Resource<List<SupplierInvoiceStage>?>>

    fun viewPendingTaskList(loadType: Int): Flow<Resource<List<PendingTask>?>>
    fun getviewDocDetails(
        srchDOCSRCHCODE: String,
        srchDOCNUMBER: String
    ): Flow<Resource<List<RequisitionDetail>?>>
}