package com.ouvrirdeveloper.data.models.responses

import com.ouvrirdeveloper.core.model.Mapper
import com.ouvrirdeveloper.domain.models.PendingTask
import com.ouvrirdeveloper.domain.models.PendingTaskDetail
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PendingTaskDetailsListResponse(
    @Json(name = "ViewMyPedingTaskListDetails")
    val viewMyPedingTaskListDetails: List<PendingTaskDetailResponse>
) : Mapper<List<PendingTaskDetail>> {
    override fun toDomainModel(): List<PendingTaskDetail> {
        return viewMyPedingTaskListDetails.map { it.toDomainModel() }
    }

}

@JsonClass(generateAdapter = true)
data class PendingTaskDetailResponse(
    @Json(name = "SrNo")
    val srNo: Long,

    @Json(name = "DOCSRCHCODE")
    val docsrchcode: String,

    @Json(name = "DOCTYPE")
    val doctype: String,

    @Json(name = "DOCNUMBER")
    val docnumber: String,

    @Json(name = "DOCDATE")
    val docdate: String,

    @Json(name = "PROJECT")
    val project: String,

    @Json(name = "DOCVALUE")
    val docvalue: Long,

    @Json(name = "BTNVIEW")
    val btnview: String,

    @Json(name = "BTNAPPROVE")
    val btnapprove: String,

    @Json(name = "BTNREJECT")
    val btnreject: String
) : Mapper<PendingTaskDetail> {
    override fun toDomainModel(): PendingTaskDetail {
        return PendingTaskDetail(
            srNo = srNo,
            docsrchcode = docsrchcode,
            doctype = doctype,
            docnumber = docnumber,
            docdate = docdate,
            project = project,
            docvalue = docvalue,
            btnview = btnview,
            btnapprove = btnapprove,
            btnreject = btnreject,
        )
    }

}