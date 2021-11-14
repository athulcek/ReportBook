package com.ouvrirdeveloper.data.api

import com.ouvrirdeveloper.core.model.BaseResponse
import com.ouvrirdeveloper.data.models.responses.*
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApiService {

    @GET("Login")
    suspend fun login(
        @Query("strUsername") strUsername: String,
        @Query("strPassword") strPassword: String,
        @Query("intAppType") AppType: Int //= 2
    ): BaseResponse<LoginResponse>

    @GET("ViewPendingTaskList")
    suspend fun viewPendingTaskList(
        @Query("intLoadType") loadType: Int,
        @Query("strDOCTYPE") docType: String = "",
        @Query("strUserid") strUserid: String = "admin",
        @Query("strSrchDate") strSrchDate: String = ""
    ): BaseResponse<PedingTaskListResponse>

    @GET("ViewPendingTaskDetails")
    suspend fun viewPendingTaskDetails(
        @Query("intLoadType") loadType: Int = 0,
        @Query("strSRCHDocument") strSRCHDocument: String,
        @Query("strUserid") strUserid: String = "admin",
        @Query("strSrchDate") strSrchDate: String = ""
    ): BaseResponse<PendingTaskDetailsListResponse>

    @GET("VIEWDocDetails")
    suspend fun viewDocDetails(
        @Query("srchDOCSRCHCODE") srchDOCSRCHCODE: String,
        @Query("strUserid") strUserid: String = "admin",
        @Query("srchDOCNUMBER") srchDOCNUMBER: String
    ): BaseResponse<ViewRequisitionDetailListResponse?>

    @GET("MaterialRequestStages")
    suspend fun materialRequestStages(
        @Query("strUserid") strUserid: String = "admin",
    ): BaseResponse<MaterialRequestStageListResponse>

    @GET("PurchaseOrderStage")
    suspend fun purchaseOrderStage(
        @Query("strUserid") strUserid: String = "admin",
    ): BaseResponse<PurchaseOrderStageListResponse>

    @GET("SiteMaterialReceiptStages")
    suspend fun siteMaterialReceiptStages(
        @Query("strUserid") strUserid: String = "admin",
    ): BaseResponse<SiteMaterialReceiptStagesListResponse>

    @GET("SupplierInvoiceStages")
    suspend fun supplierInvoiceStages(
        @Query("strUserid") strUserid: String = "admin",
    ): BaseResponse<SupplierInvoiceStagesListResponse>

}