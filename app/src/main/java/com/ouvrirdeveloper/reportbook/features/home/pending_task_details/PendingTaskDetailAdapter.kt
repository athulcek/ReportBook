package com.ouvrirdeveloper.reportbook.features.home.pending_task_details

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ouvrirdeveloper.reportbook.R
import com.ouvrirdeveloper.domain.models.PendingTaskDetail
import com.ouvrirdeveloper.reportbook.recyclerview.BaseRecyclerStateItem
import com.ouvrirdeveloper.reportbook.recyclerview.DiffCallback
import com.ouvrirdeveloper.reportbook.ui.viewholder.ErrorViewHolder
import com.ouvrirdeveloper.reportbook.ui.viewholder.ErrorViewHolder.Companion.showErrorView
import com.ouvrirdeveloper.reportbook.ui.viewholder.PendingTaskDetailsViewHolder
import com.ouvrirdeveloper.reportbook.ui.viewholder.ProgressViewHolder
import com.ouvrirdeveloper.reportbook.ui.viewholder.ProgressViewHolder.Companion.showProgress
/*

class PendingTaskDetailAdapter(
    private val viewDocument: (PendingTaskDetail) -> Unit,
    private val approve: (PendingTaskDetail) -> Unit,
    private val reject: (PendingTaskDetail) -> Unit
) :
    ListAdapter<BaseRecyclerStateItem, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun getItemViewType(position: Int) = getItem(position)?.resource ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.rv_item_progress -> ProgressViewHolder(parent)
            R.layout.rv_item_pending_task_details -> PendingTaskDetailsViewHolder(
                parent,
                viewDocument,
                approve,
                reject
            )
            else -> ErrorViewHolder(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            R.layout.rv_item_progress -> holder.showProgress(getItem(position))
            R.layout.rv_item_pending_task_details -> (holder as PendingTaskDetailsViewHolder).bind(
                getItem(
                    position
                )
            )
            else -> holder.showErrorView(getItem(position))
        }
    }


}*/
