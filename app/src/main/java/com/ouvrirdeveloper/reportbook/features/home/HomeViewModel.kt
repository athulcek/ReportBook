package com.ouvrirdeveloper.reportbook.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.ouvrirdeveloper.basearc.ui.base.BaseViewModel
import com.ouvrirdeveloper.domain.models.*
import com.ouvrirdeveloper.domain.usecases.TaskUseCase
import com.ouvrirdeveloper.domain.usecases.UserUseCase
import com.ouvrirdeveloper.reportbook.features.home.epoxy.models.ReportType
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(val taskUseCase: TaskUseCase, val userUseCase: UserUseCase) : BaseViewModel() {

    private var _currentType: ReportType? = null
    val currentType: ReportType?
        get() = _currentType

    val _onHomeClickListner = MutableSharedFlow<Pair<ReportType, Any>>()
    val onHomeClickListner = _onHomeClickListner.asSharedFlow()

    val _onTaskDetailAction = MutableSharedFlow<Any>()
    val onTaskDetailAction = _onTaskDetailAction.asSharedFlow()

    val _onTaskDetailRetry = MutableSharedFlow<Boolean>()
    val onTaskDetailRetry = _onTaskDetailRetry.asSharedFlow()

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

    val flowComplete = MediatorLiveData<Int>()

    init {

        flowComplete.postValue(0)
        flowComplete.addSource(pendingTasks) {
            if (checkStatus(it.status)) {
                flowComplete.value = flowComplete.value?.plus(1)
            }
        }
        flowComplete.addSource(materialRequestStages) {
            if (checkStatus(it.status)) {
                flowComplete.value = flowComplete.value?.plus(1)
            }
        }
        flowComplete.addSource(purchaseOrderStage) {
            if (checkStatus(it.status)) {
                flowComplete.value = flowComplete.value?.plus(1)
            }
        }
        flowComplete.addSource(siteMaterialReceiptStages) {
            if (checkStatus(it.status)) {
                flowComplete.value = flowComplete.value?.plus(1)
            }
        }
        flowComplete.addSource(supplierInvoiceStages) {
            if (checkStatus(it.status)) {
                flowComplete.value = flowComplete.value?.plus(1)
            }
        }
    }

    private fun checkStatus(status: Status) =
        status != Status.LOADING && status != Status.INITIAL


    fun getmaterialRequestStages() {
        viewModelScope.launch {
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
            taskUseCase.getPendingTaskList(1)
                .onStart { _pendingTasks.value = Resource.loading() }
                .collect {
                    _pendingTasks.value = it
                }
        }
    }

    fun initPendingTaskDetails() {
        pendingTaskDetails.value = Resource.initial(null)
    }

    fun updateConfig(remoteConfigData: RemoteConfigData) {
        userUseCase.updateConfig(remoteConfigData)
    }

    suspend fun verifyCredentialswithRemoteData(): Flow<RemoteConfigData?> {
        return flow {
            if (userUseCase.getUserFromDisk() != null)
                userUseCase.getremoteConfig().collect {
                    emit(it)
                }
            else
                emit(null)
        }

    }

    fun logout() = userUseCase.onLogout()
    fun resetComplete() {
        flowComplete.postValue(0)
    }

    fun setCurrentType(type: ReportType) {
        _currentType = type
    }

    fun taskDetailAction() = _onTaskDetailAction
    fun taskDetailRetry() = _onTaskDetailRetry
}