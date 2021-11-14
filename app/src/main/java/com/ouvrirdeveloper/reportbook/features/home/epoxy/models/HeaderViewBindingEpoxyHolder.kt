package com.ouvrirdeveloper.reportbook.features.home.epoxy.models

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.kotlinsample.helpers.ViewBindingEpoxyModelWithHolder
import com.ouvrirdeveloper.reportbook.R
import com.ouvrirdeveloper.reportbook.databinding.EpoxyItemHeaderBinding


@EpoxyModelClass(layout = R.layout.epoxy_item_header)
abstract class HeaderViewBindingEpoxyHolder :
    ViewBindingEpoxyModelWithHolder<EpoxyItemHeaderBinding>() {
    @EpoxyAttribute
    lateinit var title: String

    @EpoxyAttribute
    lateinit var onClick: ((String) -> Unit)
    override fun EpoxyItemHeaderBinding.bind() {
        btntitle.text = this@HeaderViewBindingEpoxyHolder.title
        btntitle.setOnClickListener { onClick(title) }
    }

    override fun EpoxyItemHeaderBinding.unbind() {
        btntitle.setOnClickListener(null)
    }

}