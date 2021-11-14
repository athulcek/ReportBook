package com.ouvrirdeveloper.reportbook.ui.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ouvrirdeveloper.core.extensions.bindings
import com.ouvrirdeveloper.domain.models.RequisitionDetail
import com.ouvrirdeveloper.reportbook.R
import com.ouvrirdeveloper.reportbook.databinding.RvItemRequisitionDetailBinding
import com.ouvrirdeveloper.reportbook.recyclerview.BaseRecyclerStateItem
import com.ouvrirdeveloper.reportbook.recyclerview.RequisitionDetailItem


class RequisitionDetailViewHolder(
    val view: View,
    private val viewDetails: (RequisitionDetail) -> Unit
) : RecyclerView.ViewHolder(view) {

    constructor(
        parent: ViewGroup,
        viewDetails: (RequisitionDetail) -> Unit
    ) : this(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_item_requisition_detail, parent, false),
        viewDetails
    )

    private val binding by bindings<RvItemRequisitionDetailBinding>(view)
    fun bind(item: BaseRecyclerStateItem) {
        if (item is RequisitionDetailItem) {
            binding.item = item.requisitionDetail
            binding.viewClick.setOnClickListener {
                viewDetails.invoke(item.requisitionDetail)
            }
        }


    }

}