package com.ouvrirdeveloper.reportbook.recyclerview

import androidx.recyclerview.widget.DiffUtil

class DiffCallback : DiffUtil.ItemCallback<BaseRecyclerStateItem>() {
    override fun areItemsTheSame(
        oldItem: BaseRecyclerStateItem,
        newItem: BaseRecyclerStateItem
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: BaseRecyclerStateItem,
        newItem: BaseRecyclerStateItem
    ): Boolean {
        if (oldItem is RequisitionDetailItem && newItem is RequisitionDetailItem) {
            return (oldItem.requisitionDetail == newItem.requisitionDetail)
        } else if (oldItem is PendingTaskDetailItem && newItem is PendingTaskDetailItem) {
            return (oldItem.pendingTask == newItem.pendingTask)
        } else if (oldItem is PendingTaskItem && newItem is PendingTaskItem) {
            return (oldItem.pendingTask == newItem.pendingTask)
        } else if (oldItem is BaseRecyclerStateItem.ProgressStateItem && newItem is BaseRecyclerStateItem.ProgressStateItem) {
            return (true)
        } else if (oldItem is BaseRecyclerStateItem.ErrorStateItem && newItem is BaseRecyclerStateItem.ErrorStateItem) {
            return (true)
        }
        return false
    }

}