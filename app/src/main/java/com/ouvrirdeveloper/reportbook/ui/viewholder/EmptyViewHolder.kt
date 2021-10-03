package com.ouvrirdeveloper.reportbook.ui.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ouvrirdeveloper.basearc.R
import com.ouvrirdeveloper.basearc.databinding.RvItemEmptyBinding
import com.ouvrirdeveloper.core.extensions.bindings
import com.ouvrirdeveloper.reportbook.recyclerview.BaseRecyclerStateItem

class EmptyViewHolder(
    val view: View
) : RecyclerView.ViewHolder(view) {
    companion object {
        fun <X> RecyclerView.ViewHolder.showEmptyView(
            item: X
        ) {
            if (item is BaseRecyclerStateItem.ProgressStateItem) {
                (this as ProgressViewHolder).bind(item.item)
            }
        }
    }

    constructor(
        parent: ViewGroup
    ) : this(LayoutInflater.from(parent.context).inflate(R.layout.rv_item_empty, parent, false))

    private val binding by bindings<RvItemEmptyBinding>(view)

    fun bind(item: BaseRecyclerStateItem) {
        /**No specific implementation**/
    }
}