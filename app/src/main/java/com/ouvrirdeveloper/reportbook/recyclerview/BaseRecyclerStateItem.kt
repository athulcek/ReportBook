package com.ouvrirdeveloper.reportbook.recyclerview

import com.ouvrirdeveloper.basearc.R
import com.ouvrirdeveloper.domain.models.Resource

sealed class BaseRecyclerStateItem(open val id: Long, open val resource: Int) {
    data class ProgressStateItem(val item: Resource<Boolean>) :
        BaseRecyclerStateItem(-200, R.layout.rv_item_progress)

    object EmptyStateItem : BaseRecyclerStateItem(-100, R.layout.rv_item_empty)
}

