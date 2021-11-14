package com.ouvrirdeveloper.reportbook.features.home.epoxy.models

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.airbnb.epoxy.AutoModel
import com.ouvrirdeveloper.basearc.core.extension.asColor
import com.ouvrirdeveloper.core.extensions.setOnclickCombained
import com.ouvrirdeveloper.reportbook.R
import com.ouvrirdeveloper.reportbook.databinding.EpoxyItemContentBinding
import com.ouvrirdeveloper.reportbook.viewbinding.ViewBindingKotlinModel

data class ContentModel(
    val title: String,
    val count: Long,
    @ColorRes
    val color: Int,
    val context: Context,
    val width: Int,
    val onClick: ((String) -> Unit)? = null
) : ViewBindingKotlinModel<EpoxyItemContentBinding>(R.layout.epoxy_item_content) {
    override fun EpoxyItemContentBinding.bind() {
        tvCount.text = count.toString()
        tvTitle.text = title

        setOnclickCombained(arrayOf(parentCard)) {
            onClick?.invoke(title)
        }
        viewBg.backgroundTintList = ContextCompat.getColorStateList(context, color)
        tvTitle.setTextColor(color.asColor(context))
        // computeCardHeight(context, width)
    }


    private fun computeCardHeight(context: Context, width: Int): Int {
        val fakeViewBinding: EpoxyItemContentBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.epoxy_item_content,
                null,
                false
            )
//        fakeViewBinding.setVariable(BR.data, FakeViewData.MAX_ALL.itemViewData)
        fakeViewBinding.getRoot()
            .measure(View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY), 0)
        return fakeViewBinding.getRoot().getMeasuredHeight()
    }
}
