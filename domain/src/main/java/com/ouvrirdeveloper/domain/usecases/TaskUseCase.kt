package com.ouvrirdeveloper.domain.usecases

import com.ouvrirdeveloper.domain.repositories.TaskRepository

class TaskUseCase(private val taskRepository: TaskRepository) : BaseUseCase() {

    fun getPendingTasksDb() = taskRepository.getPendingTasksDb()
    fun getViewPendingTaskDetails(strSRCHDocument: String) =
        taskRepository.getViewPendingTaskDetails(strSRCHDocument)
    fun getviewDocDetails(srchDOCSRCHCODE: String, srchDOCNUMBER: String) =
        taskRepository.getviewDocDetails(srchDOCSRCHCODE, srchDOCNUMBER)

}

