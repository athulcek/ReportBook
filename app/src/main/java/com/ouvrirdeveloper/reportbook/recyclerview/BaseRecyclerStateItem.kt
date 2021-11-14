package com.ouvrirdeveloper.reportbook.recyclerview

import android.graphics.drawable.Drawable
import com.ouvrirdeveloper.domain.models.Resource
import com.ouvrirdeveloper.reportbook.R

sealed class BaseRecyclerStateItem(open val id: Long, open val resource: Int) {
    data class ProgressStateItem(val item: Resource<Boolean>) :
        BaseRecyclerStateItem(-200, R.layout.rv_item_progress)

    data class ErrorStateItem(val icon: Drawable? = null, val message: String? = null) :
        BaseRecyclerStateItem(-300, R.layout.rv_item_error)
}

