package com.ouvrirdeveloper.data.models.responses

import com.ouvrirdeveloper.core.model.Mapper
import com.ouvrirdeveloper.domain.models.PendingTask
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PedingTaskListResponse(
    @Json(name = "ViewMyPedingTaskList")
    val viewMyPedingTaskResponse: List<ViewMyPedingTaskResponse>
) : Mapper<List<PendingTask>> {
    override fun toDomainModel(): List<PendingTask> {
        return viewMyPedingTaskResponse.map { it.toDomainModel() }
    }
}

@JsonClass(generateAdapter = true)
data class ViewMyPedingTaskResponse(
    @Json(name = "DOCSRCHCODE")
    val docsrchcode: String,

    @Json(name = "DOCTYPE")
    val doctype: String,

    @Json(name = "TOTALCOUNT")
    val totalcount: Long
) : Mapper<PendingTask> {
    override fun toDomainModel(): PendingTask {
        return PendingTask(
            id = -1L,
            docsrchcode = docsrchcode,
            doctype = doctype,
            totalcount = totalcount
        )
    }

}
