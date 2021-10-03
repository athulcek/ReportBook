package com.ouvrirdeveloper.data.models.responses

import com.ouvrirdeveloper.core.model.Mapper
import com.ouvrirdeveloper.domain.models.RequisitionDetail
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ViewRequisitionDetailListResponse(
    @Json(name = "VIEW_REQUISITION_DETAIL_LIST")
    val viewRequisitionDetail: List<ViewRequisitionDetailResponse>
) : Mapper<List<RequisitionDetail>> {
    override fun toDomainModel(): List<RequisitionDetail> {
        return viewRequisitionDetail.map { it.toDomainModel() }
    }

}

@JsonClass(generateAdapter = true)
data class ViewRequisitionDetailResponse(
    @Json(name = "ReqesitionID")
    val reqesitionID: Long,

    @Json(name = "ReqNumber")
    val reqNumber: String,

    @Json(name = "ReqDate")
    val reqDate: String,

    @Json(name = "PAprojid")
    val pAprojid: String,

    @Json(name = "Paprojname")
    val paprojname: String,

    @Json(name = "DeliveryAddress")
    val deliveryAddress: String,

    @Json(name = "ContactPerson")
    val contactPerson: String,

    @Json(name = "ContactNumber")
    val contactNumber: String,

    @Json(name = "LastModifiedBy")
    val lastModifiedBy: String,

    @Json(name = "Send")
    val send: String,

    @Json(name = "Approved")
    val approved: String,

    @Json(name = "ApprovedBy")
    val approvedBy: String,

    @Json(name = "ApprovalComments")
    val approvalComments: String,

    @Json(name = "SlNo")
    val slNo: Long,

    @Json(name = "MaterialCode")
    val materialCode: String,

    @Json(name = "MaterialName")
    val materialName: String,

    @Json(name = "Brand")
    val brand: String,

    @Json(name = "Consltapproval")
    val consltapproval: String,

    @Json(name = "CostCode")
    val costCode: String,

    @Json(name = "UOM")
    val uom: String,

    @Json(name = "Quantity")
    val quantity: Long,

    @Json(name = "RequiredDate")
    val requiredDate: String,

    @Json(name = "Remarks")
    val remarks: String,

    @Json(name = "DeliveryRemarks")
    val deliveryRemarks: String,

    @Json(name = "BOQReference")
    val boqReference: String,

    @Json(name = "Revised")
    val revised: Long,

    @Json(name = "Revision")
    val revision: Long,

    @Json(name = "RevisionDate")
    val revisionDate: String,

    @Json(name = "SendBy")
    val sendBy: String?,

    @Json(name = "ReadyForPO")
    val readyForPO: String,

    @Json(name = "PurchaseOfficer")
    val purchaseOfficer: String,

    @Json(name = "POQTYSTATUS")
    val poqtystatus: Long,

    @Json(name = "CancelQuantity")
    val cancelQuantity: Long,

    @Json(name = "CancelComments")
    val cancelComments: String,

    @Json(name = "CreatedBy")
    val createdBy: String,

    @Json(name = "CreatedOn")
    val createdOn: String,

    @Json(name = "MRStatus")
    val mrStatus: String,

    @Json(name = "ApprovedDate")
    val approvedDate: String
) : Mapper<RequisitionDetail> {
    override fun toDomainModel(): RequisitionDetail {
        return RequisitionDetail(
            reqesitionID = reqesitionID,
            reqNumber = reqNumber,
            reqDate = reqDate,
            pAprojid = pAprojid,
            paprojname = paprojname,
            deliveryAddress = deliveryAddress,
            contactPerson = contactPerson,
            contactNumber = contactNumber,
            lastModifiedBy = lastModifiedBy,
            send = send,
            approved = approved,
            approvedBy = approvedBy,
            approvalComments = approvalComments,
            slNo = slNo,
            materialCode = materialCode,
            materialName = materialName,
            brand = brand,
            consltapproval = consltapproval,
            costCode = costCode,
            uom = uom,
            quantity = quantity,
            requiredDate = requiredDate,
            remarks = remarks,
            deliveryRemarks = deliveryRemarks,
            boqReference = boqReference,
            revised = revised,
            revision = revision,
            revisionDate = revisionDate,
            sendBy = sendBy?:"",
            readyForPO = readyForPO,
            purchaseOfficer = purchaseOfficer,
            poqtystatus = poqtystatus,
            cancelQuantity = cancelQuantity,
            cancelComments = cancelComments,
            createdBy = createdBy,
            createdOn = createdOn,
            mrStatus = mrStatus,
            approvedDate = approvedDate
        )
    }

}