package com.ouvrirdeveloper.data.models.responses

import com.ouvrirdeveloper.core.model.Mapper
import com.ouvrirdeveloper.domain.models.MaterialRequestStage
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class MaterialRequestStageListResponse(
    @Json(name = "MaterialRequestStages")
    val materialRequestStagesResponse: List<MaterialRequestStageResponse>
) : Mapper<List<MaterialRequestStage>> {
    override fun toDomainModel(): List<MaterialRequestStage>? {
        return materialRequestStagesResponse.map { it.toDomainModel() }
    }

}

@JsonClass(generateAdapter = true)
data class MaterialRequestStageResponse(
    @Json(name = "DOCTYPE")
    val doctype: String,
    @Json(name = "DOCCOUNT")
    val doccount: Long
) : Mapper<MaterialRequestStage> {
    override fun toDomainModel(): MaterialRequestStage {
        return MaterialRequestStage(
            doctype = doctype,
            doccount = doccount
        )
    }

}