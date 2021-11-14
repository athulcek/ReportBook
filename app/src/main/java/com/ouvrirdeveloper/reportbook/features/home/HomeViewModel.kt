package com.ouvrirdeveloper.reportbook.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.ouvrirdeveloper.basearc.ui.base.BaseViewModel
import com.ouvrirdeveloper.domain.models.*
import com.ouvrirdeveloper.domain.usecases.TaskUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart

class HomeViewModel(val taskUseCase: TaskUseCase) : BaseViewModel() {

    private val _pendingTasks = MutableLiveData<Resource<List<PendingTask>?>>()
    val pendingTasks: LiveData<Resource<List<PendingTask>?>>
        get() = _pendingTasks

    val pendingTask = taskUseCase.getPendingTasksDb().asLiveData()

    val pendingTaskDetails = MutableLiveData<Resource<List<PendingTaskDetail>?>>()
    val materialRequestStages = MutableLiveData<Resource<List<MaterialRequestStage>?>>()

    val purchaseOrderStage = MutableLiveData<Resource<List<PurchaseOrderStage>?>>()
    val siteMaterialReceiptStages = MutableLiveData<Resource<List<SiteMaterialReceiptStage>?>>()
    val supplierInvoiceStages = MutableLiveData<Resource<List<SupplierInvoiceStage>?>>()

    val requisitionDetail = MutableLiveData<Resource<List<RequisitionDetail>?>>()


    fun getmaterialRequestStages() {
        runOnMain {
            taskUseCase.getmaterialRequestStages()
                .onStart { materialRequestStages.value = Resource.loading() }
                .collect {
                    materialRequestStages.value = it
                }
        }
    }

    fun getPurchaseOrderStage() {
        runOnMain {
            taskUseCase.getPurchaseOrderStage()
                .onStart { purchaseOrderStage.value = Resource.loading() }
                .collect {
                    purchaseOrderStage.value = it
                }
        }
    }

    fun getSiteMaterialReceiptStages() {
        runOnMain {
            taskUseCase.getSiteMaterialReceiptStages()
                .onStart { siteMaterialReceiptStages.value = Resource.loading() }
                .collect {
                    siteMaterialReceiptStages.value = it
                }
        }
    }

    fun getSupplierInvoiceStages() {
        runOnMain {
            taskUseCase.getSupplierInvoiceStages()
                .onStart { supplierInvoiceStages.value = Resource.loading() }
                .collect {
                    supplierInvoiceStages.value = it
                }
        }
    }

    fun getPendingTaskDetail(strSRCHDocument: String) {
        runOnMain {
            taskUseCase.getViewPendingTaskDetails(strSRCHDocument)
                .onStart { pendingTaskDetails.value = Resource.loading() }
                .collect {
                    pendingTaskDetails.value = it
                }
        }
    }

    fun getrequisitionDetail(srchDOCSRCHCODE: String, srchDOCNUMBER: String) {
        runOnMain {
            taskUseCase.getviewDocDetails(srchDOCSRCHCODE, srchDOCNUMBER)
                .onStart { requisitionDetail.value = Resource.loading(null) }
                .collect {
                    requisitionDetail.value = it
                }
        }
    }

    fun getPendingTasks() {
        runOnMain {
            taskUseCase.getPendingTaskList(1).collect {
                _pendingTasks.value = it

            }
        }
    }

}