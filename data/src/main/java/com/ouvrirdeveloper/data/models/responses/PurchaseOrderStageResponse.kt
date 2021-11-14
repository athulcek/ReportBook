package com.ouvrirdeveloper.data.models.responses

import com.ouvrirdeveloper.core.model.Mapper
import com.ouvrirdeveloper.domain.models.PurchaseOrderStage
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PurchaseOrderStageListResponse(
    @Json(name = "PurchaseOrderStages")
    val purchaseOrderStageResponses: List<PurchaseOrderStageResponse>
) : Mapper<List<PurchaseOrderStage>> {
    override fun toDomainModel(): List<PurchaseOrderStage> {
        return purchaseOrderStageResponses.map { it.toDomainModel() }
    }

}
@JsonClass(generateAdapter = true)
data class PurchaseOrderStageResponse(
    @Json(name = "LPODOCTYPE")
    val lpodoctype: String,
    @Json(name = "LPODOCCOUNT")
    val lpodoccount: Long,
    @Json(name = "LPODOCSRCH")
    val lpodocsrch: String
) : Mapper<PurchaseOrderStage> {
    override fun toDomainModel(): PurchaseOrderStage {
        return PurchaseOrderStage(
            lpodoctype = lpodoctype,
            lpodoccount = lpodoccount,
            lpodocsrch = lpodocsrch
        )
    }

}