package com.ouvrirdeveloper.reportbook.ui.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ouvrirdeveloper.basearc.R
import com.ouvrirdeveloper.basearc.databinding.RvItemProgressBinding
import com.ouvrirdeveloper.core.extensions.bindings
import com.ouvrirdeveloper.core.extensions.gone
import com.ouvrirdeveloper.core.extensions.show
import com.ouvrirdeveloper.domain.models.Resource
import com.ouvrirdeveloper.domain.models.Status
import com.ouvrirdeveloper.reportbook.recyclerview.BaseRecyclerStateItem


class ProgressViewHolder(
    val view: View
) : RecyclerView.ViewHolder(view) {

    companion object {
        fun <X> RecyclerView.ViewHolder.showProgress(
            item: X
        ) {
            if (item is BaseRecyclerStateItem.ProgressStateItem) {
                (this as ProgressViewHolder).bind(item.item)
            }
        }
    }

    constructor(
        parent: ViewGroup
    ) : this(
        LayoutInflater.from(parent.context).inflate(R.layout.rv_item_progress, parent, false)
    )

    private val binding by bindings<RvItemProgressBinding>(view)
    fun bind(item: Resource<Boolean>) {
        when (item.status) {
            Status.INITIAL, Status.EMPTY, Status.SUCCESS -> {
                binding.ivRetry.gone()
                binding.inLayoutProgressBar.gone()
                binding.tvLoading.gone()
            }
            Status.GENERIC_ERROR,
            Status.NETWORK_ERROR,
            Status.HTTP_ERROR -> {
                binding.inLayoutProgressBar.gone()
                binding.ivRetry.show()
                binding.tvLoading.show()
                binding.tvLoading.text = view.context.getString(R.string.download_failed)
            }
            Status.LOADING -> {
                binding.ivRetry.gone()
                binding.inLayoutProgressBar.show()
                binding.tvLoading.gone()
            }
        }
        binding.ivRetry.setOnClickListener {

        }

    }
}