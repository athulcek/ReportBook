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

    suspend fun getViewPendingTaskDetails(strSRCHDocument: String,userId:String): BaseResponse<PendingTaskDetailsListResponse> {
        return apiService.viewPendingTaskDetails(strSRCHDocument = strSRCHDocument ,strUserid=userId )
    }

    suspend fun getmaterialRequestStages(userId:String): BaseResponse<MaterialRequestStageListResponse> {
        return apiService.materialRequestStages(strUserid = userId)
    }

    suspend fun getPurchaseOrderStage(userId:String) = apiService.purchaseOrderStage(strUserid = userId)

    suspend fun getSiteMaterialReceiptStages(userId:String) = apiService.siteMaterialReceiptStages(userId)

    suspend fun getSupplierInvoiceStages(userId:String) = apiService.supplierInvoiceStages(userId)

    suspend fun getviewDocDetails(
        srchDOCSRCHCODE: String,
        srchDOCNUMBER: String,
        userId:String
    ): BaseResponse<ViewRequisitionDetailListResponse?> {
        return apiService.viewDocDetails(
            srchDOCSRCHCODE = srchDOCSRCHCODE,
            srchDOCNUMBER = srchDOCNUMBER,
                    strUserid=userId
        )
    }

    suspend fun viewPendingTaskList(
        loadType: Int,
        userId: String
    ): BaseResponse<PedingTaskListResponse> {
        return apiService.viewPendingTaskList(loadType = loadType, strUserid = userId)
    }
}