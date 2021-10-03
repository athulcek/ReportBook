package com.ouvrirdeveloper.reportbook.features.home.pending_task

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ouvrirdeveloper.basearc.R
import com.ouvrirdeveloper.domain.models.PendingTask
import com.ouvrirdeveloper.reportbook.recyclerview.BaseRecyclerStateItem
import com.ouvrirdeveloper.reportbook.recyclerview.DiffCallback
import com.ouvrirdeveloper.reportbook.ui.viewholder.EmptyViewHolder
import com.ouvrirdeveloper.reportbook.ui.viewholder.EmptyViewHolder.Companion.showEmptyView
import com.ouvrirdeveloper.reportbook.ui.viewholder.PendingTaskViewHolder
import com.ouvrirdeveloper.reportbook.ui.viewholder.ProgressViewHolder
import com.ouvrirdeveloper.reportbook.ui.viewholder.ProgressViewHolder.Companion.showProgress

class PendingTaskAdapter(private val onSingleClick: (PendingTask) -> Unit) :
    ListAdapter<BaseRecyclerStateItem, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun getItemViewType(position: Int) = getItem(position)?.resource ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.rv_item_progress -> ProgressViewHolder(parent)
            R.layout.rv_item_pending_task -> PendingTaskViewHolder(parent, onSingleClick)
            else -> EmptyViewHolder(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            R.layout.rv_item_progress -> holder.showProgress(getItem(position))
            R.layout.rv_item_pending_task -> (holder as PendingTaskViewHolder).bind(getItem(position))
            else -> holder.showEmptyView(getItem(position))
        }
    }


}