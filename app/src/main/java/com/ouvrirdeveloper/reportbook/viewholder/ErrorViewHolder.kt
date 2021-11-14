package com.ouvrirdeveloper.reportbook.ui.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ouvrirdeveloper.core.extensions.bindings
import com.ouvrirdeveloper.reportbook.R
import com.ouvrirdeveloper.reportbook.databinding.RvItemErrorBinding
import com.ouvrirdeveloper.reportbook.recyclerview.BaseRecyclerStateItem

class ErrorViewHolder(
    val view: View,
    val reLoad:(() -> Unit)? = null
) : RecyclerView.ViewHolder(view) {
    companion object {
        fun <X> RecyclerView.ViewHolder.showErrorView(
            item: X
        ) {
            if (item is BaseRecyclerStateItem.ErrorStateItem) {
                (this as ErrorViewHolder).bind(item)
            }
        }
    }

    constructor(
        parent: ViewGroup,
        reLoad: (() -> Unit)? = null
    ) : this(
        LayoutInflater.from(parent.context).inflate(R.layout.rv_item_error, parent, false),
        reLoad
    )

    private val binding by bindings<RvItemErrorBinding>(view)

    fun bind(item: BaseRecyclerStateItem.ErrorStateItem) {
        item.icon?.apply {
            binding.imvLogo.background = this
        }
        item.message?.apply {
            binding.tvMessage.text = this
        }
    }
}