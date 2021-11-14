package com.ouvrirdeveloper.data.models.responses

import com.ouvrirdeveloper.core.model.Mapper
import com.ouvrirdeveloper.domain.models.SiteMaterialReceiptStage
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SiteMaterialReceiptStagesListResponse(
    @Json(name = "SiteMaterialReceiptStages")
    val purchaseOrderStageResponses: List<SiteMaterialReceiptStageResponse>
) : Mapper<List<SiteMaterialReceiptStage>> {
    override fun toDomainModel(): List<SiteMaterialReceiptStage> {
        return purchaseOrderStageResponses.map { it.toDomainModel() }
    }

}

@JsonClass(generateAdapter = true)
data class SiteMaterialReceiptStageResponse(
    @Json(name = "SMRDOCTYPE")
    val SMRDOCTYPE: String,
    @Json(name = "SMRDOCCOUNT")
    val SMRDOCCOUNT: Long,
    @Json(name = "SMRDOCSRCH")
    val SMRDOCSRCH: String
) : Mapper<SiteMaterialReceiptStage> {
    override fun toDomainModel(): SiteMaterialReceiptStage {
        return SiteMaterialReceiptStage(
            SMRDOCTYPE = SMRDOCTYPE,
            SMRDOCCOUNT = SMRDOCCOUNT,
            SMRDOCSRCH = SMRDOCSRCH
        )
    }

}