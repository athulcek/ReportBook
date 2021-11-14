package com.ouvrirdeveloper.domain.usecases

import com.ouvrirdeveloper.domain.repositories.TaskRepository

class TaskUseCase(private val taskRepository: TaskRepository) : BaseUseCase() {

    fun getPendingTasksDb() = taskRepository.getPendingTasksDb()

    fun getViewPendingTaskDetails(strSRCHDocument: String) =
        taskRepository.getViewPendingTaskDetails(strSRCHDocument)

    fun getmaterialRequestStages() =
        taskRepository.getmaterialRequestStages()

    fun getPurchaseOrderStage() =
        taskRepository.getPurchaseOrderStage()

    fun getSiteMaterialReceiptStages() =
        taskRepository.getSiteMaterialReceiptStages()

    fun getSupplierInvoiceStages() =
        taskRepository.getSupplierInvoiceStages()

    fun getviewDocDetails(
        srchDOCSRCHCODE: String,
        srchDOCNUMBER: String
    ) = taskRepository.getviewDocDetails(srchDOCSRCHCODE, srchDOCNUMBER)

    fun getPendingTaskList(loadType: Int) =
        taskRepository.viewPendingTaskList(loadType)


}

