package com.ouvrirdeveloper.reportbook.features.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.ouvrirdeveloper.basearc.ui.base.BaseViewModel
import com.ouvrirdeveloper.domain.helpers.extensions.ifLet
import com.ouvrirdeveloper.domain.models.PendingTaskDetail
import com.ouvrirdeveloper.domain.models.RequisitionDetail
import com.ouvrirdeveloper.domain.models.Resource
import com.ouvrirdeveloper.domain.models.Status
import com.ouvrirdeveloper.domain.usecases.TaskUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart

class HomeViewModel(val taskUseCase: TaskUseCase) : BaseViewModel() {

    val pendingTask = taskUseCase.getPendingTasksDb().asLiveData()

    val pendingTaskDetails = MutableLiveData<List<PendingTaskDetail>>()

    val requisitionDetail = MutableLiveData<Resource<List<RequisitionDetail>>>()


    fun getPendingTaskDetail(strSRCHDocument: String) {
        runOnMain {
            taskUseCase.getViewPendingTaskDetails(strSRCHDocument).collect {
                when (it.status) {
                    Status.SUCCESS -> ifLet(it.data) {
                        pendingTaskDetails.value = it.flatMap { it }
                    }
                    Status.GENERIC_ERROR -> TODO()
                    Status.NETWORK_ERROR -> TODO()
                    Status.HTTP_ERROR -> TODO()
                    Status.LOADING -> TODO()
                    Status.INITIAL -> TODO()
                    Status.EMPTY -> TODO()
                }
            }
        }
    }

    fun getrequisitionDetail(srchDOCSRCHCODE: String, srchDOCNUMBER: String) {
        runOnMain {
            taskUseCase.getviewDocDetails(srchDOCSRCHCODE, srchDOCNUMBER)
                .onStart {
                    requisitionDetail.value = Resource.loading(null)
                }
                .collect {
                    when (it.status) {
                        Status.SUCCESS -> ifLet(it.data) {
                            requisitionDetail.value = Resource.success(it.flatMap { it })
                        }
                        Status.GENERIC_ERROR,
                        Status.NETWORK_ERROR,
                        Status.HTTP_ERROR,
                        Status.LOADING -> requisitionDetail.value = Resource.loading(null)
                        Status.INITIAL -> TODO()
                        Status.EMPTY -> TODO()
                    }
                }
        }
    }

}