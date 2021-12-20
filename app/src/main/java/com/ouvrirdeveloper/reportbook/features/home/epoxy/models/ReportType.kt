package com.ouvrirdeveloper.reportbook.features.home.epoxy.models

sealed class ReportType {
    object ApprovalPendings : ReportType()
    object MaterialRequestStages : ReportType()
    object SiteMaterialReceiptStage : ReportType()
    object PurchaseOrderStage : ReportType()
    object SupplierInvoiceStage : ReportType()
}
