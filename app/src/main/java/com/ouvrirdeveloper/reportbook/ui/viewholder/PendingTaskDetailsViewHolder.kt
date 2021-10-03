package com.ouvrirdeveloper.reportbook.ui.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ouvrirdeveloper.basearc.R
import com.ouvrirdeveloper.basearc.databinding.RvItemPendingTaskDetailsBinding
import com.ouvrirdeveloper.core.extensions.bindings
import com.ouvrirdeveloper.domain.models.PendingTaskDetail
import com.ouvrirdeveloper.reportbook.recyclerview.BaseRecyclerStateItem
import com.ouvrirdeveloper.reportbook.recyclerview.PendingTaskDetailItem


class PendingTaskDetailsViewHolder(
    val view: View,
    private val viewDocument: (PendingTaskDetail) -> Unit,
    private val approve: (PendingTaskDetail) -> Unit,
    private val reject: (PendingTaskDetail) -> Unit
) : RecyclerView.ViewHolder(view) {

    constructor(
        parent: ViewGroup,
        viewDocument: (PendingTaskDetail) -> Unit,
        approve: (PendingTaskDetail) -> Unit,
        reject: (PendingTaskDetail) -> Unit
    ) : this(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_item_pending_task_details, parent, false),
        viewDocument,
        approve,
        reject
    )

    private val binding by bindings<RvItemPendingTaskDetailsBinding>(view)
    fun bind(item: BaseRecyclerStateItem) {
        if (item is PendingTaskDetailItem) {
            binding.task = item.pendingTask
            binding.btnapprove.setOnClickListener {
                approve.invoke(item.pendingTask)
            }
            binding.btnview.setOnClickListener {
                viewDocument.invoke(item.pendingTask)
            }
        }


    }

}