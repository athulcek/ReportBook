package com.ouvrirdeveloper.data.models.responses

import com.ouvrirdeveloper.core.model.Mapper
import com.ouvrirdeveloper.domain.models.SupplierInvoiceStage
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SupplierInvoiceStagesListResponse(
    @Json(name = "SupplierInvoiceStages")
    val supplierInvoiceStages: List<SupplierInvoiceStagesResponse>
) : Mapper<List<SupplierInvoiceStage>> {
    override fun toDomainModel(): List<SupplierInvoiceStage> {
        return supplierInvoiceStages.map { it.toDomainModel() }
    }
}

@JsonClass(generateAdapter = true)
data class SupplierInvoiceStagesResponse(
    @Json(name = "CERTDOCTYPE")
    val certdoctype: String,
    @Json(name = "CERTDOCCOUNT")
    val certdoccount: Long,
    @Json(name = "CERTDOCSRCH")
    val certdocsrch: String
) : Mapper<SupplierInvoiceStage> {
    override fun toDomainModel(): SupplierInvoiceStage {
        return SupplierInvoiceStage(
            certdoctype = certdoctype,
            certdoccount = certdoccount,
            certdocsrch = certdocsrch
        )
    }

}
