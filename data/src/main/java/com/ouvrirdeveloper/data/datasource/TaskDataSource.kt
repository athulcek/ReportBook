package com.ouvrirdeveloper.data.datasource

import com.ouvrirdeveloper.core.model.BaseResponse
import com.ouvrirdeveloper.data.api.UserApiService
import com.ouvrirdeveloper.data.database.dao.PendingTaskDao
import com.ouvrirdeveloper.data.models.responses.MaterialRequestStageListResponse
import com.ouvrirdeveloper.data.models.responses.PedingTaskListResponse
import com.ouvrirdeveloper.data.models.responses.PendingTaskDetailsListResponse
import com.ouvrirdeveloper.data.models.responses.ViewRequisitionDetailListResponse

class TaskDataSource(
    private val apiService: UserApiService,
    private val pendingTaskDao: PendingTaskDao
) {
    fun getPendingTasksDb() = pendingTaskDao.getPendingTasksDb()

    suspend fun getViewPendingTaskDetails(strSRCHDocument: String): BaseResponse<PendingTaskDetailsListResponse> {
        return apiService.viewPendingTaskDetails(strSRCHDocument = strSRCHDocument)
    }

    suspend fun getmaterialRequestStages(): BaseResponse<MaterialRequestStageListResponse> {
        return apiService.materialRequestStages()
    }

    suspend fun getPurchaseOrderStage() = apiService.purchaseOrderStage()

    suspend fun getSiteMaterialReceiptStages() = apiService.siteMaterialReceiptStages()

    suspend fun getSupplierInvoiceStages() = apiService.supplierInvoiceStages()

    suspend fun getviewDocDetails(
        srchDOCSRCHCODE: String,
        srchDOCNUMBER: String
    ): BaseResponse<ViewRequisitionDetailListResponse?> {
        return apiService.viewDocDetails(
            srchDOCSRCHCODE = srchDOCSRCHCODE,
            srchDOCNUMBER = srchDOCNUMBER
        )
    }

    suspend fun viewPendingTaskList(loadType: Int): BaseResponse<PedingTaskListResponse> {
        return apiService.viewPendingTaskList(loadType)
    }
}