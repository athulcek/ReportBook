package com.ouvrirdeveloper.reportbook.features.home.epoxy

import android.content.Context
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.group
import com.airbnb.epoxy.kotlinsample.helpers.carouselNoSnapBuilder
import com.ouvrirdeveloper.basearc.core.extension.asString
import com.ouvrirdeveloper.domain.helpers.extensions.ifLet
import com.ouvrirdeveloper.domain.models.*
import com.ouvrirdeveloper.reportbook.R
import com.ouvrirdeveloper.reportbook.features.home.epoxy.data.HomeData
import com.ouvrirdeveloper.reportbook.features.home.epoxy.models.*

class HomeEpoxyController(val context: Context, val onClick: ((String) -> Unit)? = null) :
    EpoxyController() {

    lateinit var width: String
    val itemsPerPage = 2.45f

    init {
        width = computeCardWidth(context).toString()
    }

    var isLoading: Boolean = true
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    var pending: List<PendingTask> = emptyList()
    var materialRequestStage: List<MaterialRequestStage> = emptyList()
    var siteMaterialReceiptStage: List<SiteMaterialReceiptStage> = emptyList()
    var purchaseOrderStage: List<PurchaseOrderStage> = emptyList()
    var supplierInvoiceStage: List<SupplierInvoiceStage> = emptyList()

    override fun buildModels() {
        buildHeaderView()
        if (pending.isNotEmpty()) {
            buildPendingView()
        }
        if (materialRequestStage.isNotEmpty()) {
            buildMaterialRequestStagesView()
        }
        if (purchaseOrderStage.isNotEmpty()) {
            buildPurchaseOrderStageView()
        }

        if (siteMaterialReceiptStage.isNotEmpty()) {
            buildSiteMaterialReceiptStageView()
        }
        if (supplierInvoiceStage.isNotEmpty()) {
            buildSupplierInvoiceStageView()
        }

    }

    private fun buildSiteMaterialReceiptStageView() {
        group {
            id("group_card_site_material_receipt_stages")
            layout(R.layout.epoxy_card_group)
            group {
                id("group_site_material_receipt_stages")
                layout(R.layout.epoxy_vertical_linear_group)

                contentHeaderViewBindingEpoxyHolder {
                    title(R.string.site_material_receipt_stages.asString(context = this@HomeEpoxyController.context))
                    id("site_material_receipt_stages")
                }
                /*dividerViewBindingEpoxyHolder {
                    id("site_material_receipt_stages_divider")
                }*/
                verticalGridCarousel {
                    id("siteMaterialReceiptStage")
                    models(
                        HomeData.getSiteMaterialReceiptStage(
                            this@HomeEpoxyController.context,
                            this@HomeEpoxyController.siteMaterialReceiptStage,
                            this@HomeEpoxyController.width
                        ).map {
                            it.id(it.title)
                        }
                    )
                }

            }
        }
        /*carouselNoSnapBuilder {
            id("siteMaterialReceiptStage")
            numViewsToShowOnScreen(this@HomeEpoxyController.itemsPerPage)
            this@HomeEpoxyController.siteMaterialReceiptStage.forEach { item ->
                contentViewBindingEpoxyHolder {
                    id(item.SMRDOCTYPE)
                    title(item.SMRDOCTYPE)
                    count(item.SMRDOCCOUNT)
                    color(R.color.secondaryColor)
                    context(this@HomeEpoxyController.context)
                    width(this@HomeEpoxyController.width.toInt())
                    onClick {
                        this@HomeEpoxyController.onClick?.invoke(it)
                    }
                }
            }
        }*/
    }

    private fun buildPurchaseOrderStageView() {
        group {
            id("group_card_purchase_order_states")
            layout(R.layout.epoxy_card_group)
            group {
                id("group_purchase_order_states")
                layout(R.layout.epoxy_vertical_linear_group)

                contentHeaderViewBindingEpoxyHolder {
                    title(R.string.purchase_order_states.asString(context = this@HomeEpoxyController.context))
                    id("purchase_order_states")
                }
               /* dividerViewBindingEpoxyHolder {
                    id("purchase_order_states_divider")
                }*/
                verticalGridCarousel {
                    id("purchaseOrderStage")
                    models(
                        HomeData.getPurchaseOrderStage(
                            this@HomeEpoxyController.context,
                            this@HomeEpoxyController.purchaseOrderStage,
                            this@HomeEpoxyController.width
                        ).map {
                            it.id(it.title)
                        }
                    )
                }
            }
        }

        /*carouselNoSnapBuilder {
            id("purchaseOrderStage")
            numViewsToShowOnScreen(this@HomeEpoxyController.itemsPerPage)
            this@HomeEpoxyController.purchaseOrderStage.forEach { item ->
                contentViewBindingEpoxyHolder {

                    id(item.lpodoctype)
                    title(item.lpodoctype)
                    count(item.lpodoccount)
                    color(R.color.secondaryColor)
                    context(this@HomeEpoxyController.context)
                    width(this@HomeEpoxyController.width.toInt())
                    onClick {
                        this@HomeEpoxyController.onClick?.invoke(it)
                    }
                }
            }
        }*/
    }

    private fun buildSupplierInvoiceStageView() {
        group {
            id("group_card_supplier_invoices")
            layout(R.layout.epoxy_card_group)
            group {
                id("group_supplier_invoices")
                layout(R.layout.epoxy_vertical_linear_group)


                contentHeaderViewBindingEpoxyHolder {
                    title(R.string.supplier_invoices.asString(context = this@HomeEpoxyController.context))
                    id("supplier_invoices")
                }
               /* dividerViewBindingEpoxyHolder {
                    id("supplier_invoices_divider")
                }*/
                verticalGridCarousel {
                    id("supplierInvoiceStage")
                    models(
                        HomeData.getSupplierInvoiceStage(
                            this@HomeEpoxyController.context,
                            this@HomeEpoxyController.supplierInvoiceStage,
                            this@HomeEpoxyController.width
                        ).map {
                            it.id(it.title)
                        }
                    )
                }
            }
        }

        /*carouselNoSnapBuilder {
            id("supplierInvoiceStage")
            this@HomeEpoxyController.supplierInvoiceStage.forEach { item ->
                contentViewBindingEpoxyHolder {
                    id(item.certdoctype)
                    title(item.certdoctype)
                    count(item.certdoccount)
                    color(R.color.secondaryColor)
                    context(this@HomeEpoxyController.context)
                    width(this@HomeEpoxyController.width.toInt())
                    onClick {
                        this@HomeEpoxyController.onClick?.invoke(it)
                    }
                }
            }
        }*/
    }

    private fun buildMaterialRequestStagesView() {
        group {
            id("group_card_material_request_stages")
            layout(R.layout.epoxy_card_group)
            group {
                id("group_material_request_stages")
                layout(R.layout.epoxy_vertical_linear_group)

                contentHeaderViewBindingEpoxyHolder {
                    title(R.string.material_request_stages.asString(context = this@HomeEpoxyController.context))
                    id("material_request_stages")
                }
              /*  dividerViewBindingEpoxyHolder {
                    id("material_request_stages_divider")
                }*/

                verticalGridCarousel {
                    id("getmaterial_request_stages")
                    models(
                        HomeData.getMaterialRequestStages(
                            this@HomeEpoxyController.context,
                            this@HomeEpoxyController.materialRequestStage,
                            this@HomeEpoxyController.width
                        ).map {
                            it.id(it.title)
                        }
                    )
                }
            }
        }

        /*carouselNoSnapBuilder {
            id("getmaterial_request_stages")
            numViewsToShowOnScreen(this@HomeEpoxyController.itemsPerPage)
            this@HomeEpoxyController.materialRequestStage.forEach { item ->
                contentViewBindingEpoxyHolder {
                    id(item.doctype)
                    title(item.doctype)
                    count(item.doccount)
                    color(R.color.secondaryColor)
                    context(this@HomeEpoxyController.context)
                    width(this@HomeEpoxyController.width.toInt())
                    onClick {
                        this@HomeEpoxyController.onClick?.invoke(it)
                    }
                }
            }
        }*/
    }

    private fun buildHeaderView() {
        headerViewBindingEpoxyHolder {
            id("cheque_reminder")
            title(R.string.cheque_reminder.asString(this@HomeEpoxyController.context))
            onClick {

            }
        }
        carouselNoSnapBuilder {
            hasFixedSize(true)
            numViewsToShowOnScreen(2f)

            id("carousel_cheque_reminder")
            headerViewBindingEpoxyHolder {
                id("cheque_summary")
                title(R.string.cheque_summary.asString(this@HomeEpoxyController.context))
                onClick {

                }
            }
            headerViewBindingEpoxyHolder {
                id("current_month_cheque")
                title(R.string.current_month_cheque.asString(this@HomeEpoxyController.context))
                onClick {
                    this@HomeEpoxyController.context
                }
            }
        }
    }

    private fun buildPendingView() {
        group {
            id("group_card_my_approval_pendings_divider")
            layout(R.layout.epoxy_card_group)
            group {
                id("group_my_approval_pendings_divider")
                layout(R.layout.epoxy_vertical_linear_group)

                contentHeaderViewBindingEpoxyHolder {
                    title(R.string.my_approval_pendings.asString(context = this@HomeEpoxyController.context))
                    id("my_approval_pendings")
                }
              /*  dividerViewBindingEpoxyHolder {
                    id("my_approval_pendings_divider")
                }*/
                /* carouselNoSnapBuilder {
             id("getmy_approval_pendings")
             numViewsToShowOnScreen(this@HomeEpoxyController.itemsPerPage)
             this@HomeEpoxyController.pending.forEach { item ->
                 contentViewBindingEpoxyHolder {
                     id(item.doctype)
                     title(item.doctype)
                     count(item.totalcount)
                     color(R.color.secondaryColor)
                     context(this@HomeEpoxyController.context)
                     width(this@HomeEpoxyController.width.toInt())
                     onClick {
                         this@HomeEpoxyController.onClick?.invoke(it)
                     }
                 }
             }
         }*/
                verticalGridCarousel {
                    id("getmy_approval_pendings")
                    models(
                        HomeData.getMyApprovalPendings(
                            this@HomeEpoxyController.context,
                            this@HomeEpoxyController.pending,
                            this@HomeEpoxyController.width
                        ).map {
                            it.id(it.title)
                        }
                    )
                }

            }
        }
    }


    private fun computeCardWidth(context: Context): Int {
        return (context.getResources().getDisplayMetrics().widthPixels
                - context.getResources().getDimensionPixelSize(R.dimen.peek_width)
                - context.getResources().getDimensionPixelSize(R.dimen.item_spacing))
    }

    fun doForPendingTasks(items: Resource<List<PendingTask>?>) {
        if (items.isSuccess()) {
            ifLet(items.data) {
                pending = it.flatMap { it }
                requestModelBuild()
            }
        }
    }

    fun doForMaterialRequestStages(items: Resource<List<MaterialRequestStage>?>) {
        if (items.isSuccess()) {
            ifLet(items.data) {
                materialRequestStage = it.flatMap { it }
                requestModelBuild()
            }
        }
    }

    fun doForSiteMaterialReceiptStages(items: Resource<List<SiteMaterialReceiptStage>?>) {
        if (items.isSuccess()) {
            ifLet(items.data) {
                siteMaterialReceiptStage = it.flatMap { it }
                requestModelBuild()
            }
        }
    }

    fun doForPurchaseOrderStage(items: Resource<List<PurchaseOrderStage>?>) {
        if (items.isSuccess()) {
            ifLet(items.data) {
                purchaseOrderStage = it.flatMap { it }
                requestModelBuild()
            }
        }
    }

    fun doForSupplierInvoiceStages(items: Resource<List<SupplierInvoiceStage>?>) {
        if (items.isSuccess()) {
            ifLet(items.data) {
                supplierInvoiceStage = it.flatMap { it }
                requestModelBuild()
            }
        }
    }
}