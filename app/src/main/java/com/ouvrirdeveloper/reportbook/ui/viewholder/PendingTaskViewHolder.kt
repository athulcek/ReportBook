package com.ouvrirdeveloper.reportbook.ui.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ouvrirdeveloper.basearc.R
import com.ouvrirdeveloper.basearc.databinding.RvItemPendingTaskBinding
import com.ouvrirdeveloper.core.extensions.bindings
import com.ouvrirdeveloper.core.extensions.setOnclickCombained
import com.ouvrirdeveloper.domain.models.PendingTask
import com.ouvrirdeveloper.reportbook.recyclerview.BaseRecyclerStateItem
import com.ouvrirdeveloper.reportbook.recyclerview.PendingTaskItem


class PendingTaskViewHolder(
    val view: View,
    private val onSingleClick: (PendingTask) -> Unit
) : RecyclerView.ViewHolder(view) {

    constructor(
        parent: ViewGroup,
        onSingleClick: (PendingTask) -> Unit
    ) : this(
        LayoutInflater.from(parent.context).inflate(R.layout.rv_item_pending_task, parent, false),
        onSingleClick
    )

    private val binding by bindings<RvItemPendingTaskBinding>(view)
    fun bind(item: BaseRecyclerStateItem) {
        if (item is PendingTaskItem) {
            binding.tvCount.text = item.pendingTask.totalcount.toString()
            binding.tvTitle.text = item.pendingTask.doctype

            setOnclickCombained(arrayOf(binding.tvTitle,binding.tvTitle, binding.tvCount)) {
                onSingleClick.invoke(item.pendingTask)
            }
        }


    }

}