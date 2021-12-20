package com.ouvrirdeveloper.reportbook.features.home.pending_task_details

import android.view.View
import android.widget.TextView
import com.ouvrirdeveloper.core.extensions.date.format
import com.ouvrirdeveloper.core.extensions.date.toDate
import com.ouvrirdeveloper.core.extensions.date.today
import com.ouvrirdeveloper.core.extensions.date.yesterday
import com.ouvrirdeveloper.core.extensions.gone
import com.ouvrirdeveloper.core.extensions.makeInvisible
import com.ouvrirdeveloper.core.extensions.makeVisible
import com.ouvrirdeveloper.domain.models.PendingTaskDetail
import com.ouvrirdeveloper.reportbook.R
import com.ouvrirdeveloper.reportbook.databinding.EpoxyItemPendingTaskDetailsBinding
import com.ouvrirdeveloper.reportbook.viewbinding.ViewBindingKotlinModel

data class PendingTaskDetailsEpoxyModel(
    val uniqueId: Any? = null,
    private val item: Any,
    private val action: (Any) -> Unit
) : ViewBindingKotlinModel<EpoxyItemPendingTaskDetailsBinding>(R.layout.epoxy_item_pending_task_details) {
    override fun EpoxyItemPendingTaskDetailsBinding.bind() {
        when (item) {
            is PendingTaskDetail -> {
                setText(item.docnumber.trim(), item.docdate, item.project.toString(), "")
            }
        }
        parentLayout.setOnClickListener {
            action.invoke(item)
        }
    }

    private fun EpoxyItemPendingTaskDetailsBinding.setText(
        tv1: String?,
        date: String,
        tv3: String?,
        tv4: String?
    ) {
        tvText1.apply {
            text = tv1
            if (tv1.isNullOrEmpty()) {
                makeInvisible()
            } else {
                makeVisible()
            }
        }
        setDate(tvText2, date)
        tvText3.apply {
            text = tv3
            if (tv3.isNullOrEmpty()) {
                makeInvisible()
            } else {
                makeVisible()
            }
        }
        tvText4.apply {
            text = tv4
            if (tv4.isNullOrEmpty()) {
                gone()
            } else {
                makeVisible()
            }
        }
    }

    private fun setDate(textView: TextView, docdate: String) {
        textView.apply {
            val date = docdate.toDate("yyyy-MM-dd'T'HH:mm:ss")
            text = when {
                date == today() -> "Today"
                date == yesterday() -> "Yesterday"
                else -> date.format("dd MMM yyyy")
            }

        }
    }

    override fun unbind(view: View) {
        super.unbind(view)
    }
}
