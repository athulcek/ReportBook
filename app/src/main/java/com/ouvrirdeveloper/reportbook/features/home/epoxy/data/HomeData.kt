package com.ouvrirdeveloper.reportbook.features.home.epoxy.data

import android.content.Context
import com.airbnb.epoxy.AutoModel
import com.ouvrirdeveloper.reportbook.R
import com.ouvrirdeveloper.domain.models.*
import com.ouvrirdeveloper.reportbook.features.home.epoxy.models.ContentModel

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
        onClick: ((String) -> Unit)? = null
    ): List<ContentModel> {
        val items = mutableListOf<ContentModel>()
        for (item in pending) {
            val kk= ContentModel(
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
        width: String
    ): List<ContentModel> {
        val items = mutableListOf<ContentModel>()
        for (item in materialRequestStages) {
            items.add(
                ContentModel(
                    item.doctype,
                    item.doccount,
                    R.color.secondaryColor,
                    context,
                    width.toInt()
                )
            )
        }
        return items
    }

    fun getSiteMaterialReceiptStage(
        context: Context,
        siteMaterialReceiptStage: List<SiteMaterialReceiptStage>,
        width: String
    ): List<ContentModel> {
        val items = mutableListOf<ContentModel>()
        for (item in siteMaterialReceiptStage) {
            items.add(
                ContentModel(
                    item.SMRDOCTYPE,
                    item.SMRDOCCOUNT,
                    R.color.secondaryColor,
                    context,
                    width.toInt()
                )
            )
        }
        return items
    }

    fun getPurchaseOrderStage(
        context: Context,
        purchaseOrderStage: List<PurchaseOrderStage>,
        width: String
    ): List<ContentModel> {
        val items = mutableListOf<ContentModel>()
        for (item in purchaseOrderStage) {
            items.add(
                ContentModel(
                    item.lpodoctype,
                    item.lpodoccount,
                    R.color.secondaryColor,
                    context,
                    width.toInt()
                )
            )
        }
        return items
    }

    fun getSupplierInvoiceStage(
        context: Context,
        supplierInvoiceStage: List<SupplierInvoiceStage>,
        width: String
    ): List<ContentModel> {
        val items = mutableListOf<ContentModel>()
        for (item in supplierInvoiceStage) {
            items.add(
                ContentModel(
                    item.certdoctype,
                    item.certdoccount,
                    R.color.secondaryColor,
                    context,
                    width.toInt()
                )
            )
        }
        return items
    }
}

