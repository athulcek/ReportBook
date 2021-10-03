package com.ouvrirdeveloper.reportbook.recyclerview

import com.ouvrirdeveloper.basearc.R
import com.ouvrirdeveloper.domain.models.PendingTask
import com.ouvrirdeveloper.domain.models.PendingTaskDetail
import com.ouvrirdeveloper.domain.models.RequisitionDetail

data class PendingTaskDetailItem(
    override var id: Long, val pendingTask: PendingTaskDetail
) : BaseRecyclerStateItem(id, R.layout.rv_item_pending_task_details)


data class PendingTaskItem(
    override var id: Long, val pendingTask: PendingTask
) : BaseRecyclerStateItem(id, R.layout.rv_item_pending_task)


data class RequisitionDetailItem(
    override var id: Long, val requisitionDetail: RequisitionDetail
) : BaseRecyclerStateItem(id, R.layout.rv_item_requisition_detail)
