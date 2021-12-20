package com.ouvrirdeveloper.reportbook.features.home.epoxy.data

import android.content.Context
import com.ouvrirdeveloper.domain.models.*
import com.ouvrirdeveloper.reportbook.R
import com.ouvrirdeveloper.reportbook.features.home.epoxy.models.ContentModel
import com.ouvrirdeveloper.reportbook.features.home.pending_task_details.PendingTaskDetailsEpoxyModel

object HomeData {


    private fun computeCardWidth(context: Context): Int {
        return (context.getResources().getDisplayMetrics().widthPixels
                - context.getResources().getDimensionPixelSize(R.dimen.peek_width)
                - context.getResources().getDimensionPixelSize(R.dimen.item_spacing))
    }

    fun getMyApprovalPendings(
        context: Context,
        pending: List<PendingTask>,
        width: String,
        onClick: ((Any) -> Unit)? = null
    ): List<ContentModel> {
        val items = mutableListOf<ContentModel>()
        for (item in pending) {
            val kk = ContentModel(
                uniqueId = item.docsrchcode,
                title = item.doctype,
                count = item.totalcount,
                color = R.color.secondaryColor,
                context = context,
                width = width.toInt(),
                onClick = onClick
            )

            items.add(kk)
        }
        return items

    }

    fun getMaterialRequestStages(
        context: Context,
        materialRequestStages: List<MaterialRequestStage>,
        width: String,
        onClick: ((Any) -> Unit)? = null
    ): List<ContentModel> {
        val items = mutableListOf<ContentModel>()
        for (item in materialRequestStages) {
            items.add(
                ContentModel(
                    item.doctype,
                    item.doctype,
                    item.doccount,
                    R.color.secondaryColor,
                    context,
                    width.toInt(),
                    onClick = onClick
                )
            )
        }
        return items
    }

    fun getSiteMaterialReceiptStage(
        context: Context,
        siteMaterialReceiptStage: List<SiteMaterialReceiptStage>,
        width: String,
        onClick: ((Any) -> Unit)? = null
    ): List<ContentModel> {
        val items = mutableListOf<ContentModel>()
        for (item in siteMaterialReceiptStage) {
            items.add(
                ContentModel(
                    null,
                    item.SMRDOCTYPE,
                    item.SMRDOCCOUNT,
                    R.color.secondaryColor,
                    context,
                    width.toInt(),
                    onClick = onClick
                )
            )
        }
        return items
    }

    fun getPurchaseOrderStage(
        context: Context,
        purchaseOrderStage: List<PurchaseOrderStage>,
        width: String,
        onClick: ((Any) -> Unit)? = null
    ): List<ContentModel> {
        val items = mutableListOf<ContentModel>()
        for (item in purchaseOrderStage) {
            items.add(
                ContentModel(
                    null,
                    item.lpodoctype,
                    item.lpodoccount,
                    R.color.secondaryColor,
                    context,
                    width.toInt(),
                    onClick = onClick
                )
            )
        }
        return items
    }

    fun getSupplierInvoiceStage(
        context: Context,
        supplierInvoiceStage: List<SupplierInvoiceStage>,
        width: String,
        onClick: ((Any) -> Unit)? = null
    ): List<ContentModel> {
        val items = mutableListOf<ContentModel>()
        for (item in supplierInvoiceStage) {
            items.add(
                ContentModel(
                    null,
                    item.certdoctype,
                    item.certdoccount,
                    R.color.secondaryColor,
                    context,
                    width.toInt(),
                    onClick = onClick
                )
            )
        }
        return items
    }

    fun getPendingTaskDetails(
        pendingTaskDetails: List<Any>,
        action: (Any) -> Unit,
    ): List<PendingTaskDetailsEpoxyModel> {

        val items = mutableListOf<PendingTaskDetailsEpoxyModel>()
        for (item in pendingTaskDetails) {
            when (item) {
                is PendingTaskDetail -> {
                    items.add(
                        PendingTaskDetailsEpoxyModel(
                            item.docnumber,
                            item,
                            action
                        ).apply {
                            id(uniqueId.toString())
                        }
                    )
                }
            }

        }
        return items
    }
}

