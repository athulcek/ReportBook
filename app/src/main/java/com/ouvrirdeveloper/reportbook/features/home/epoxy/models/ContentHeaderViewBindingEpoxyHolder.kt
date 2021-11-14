package com.ouvrirdeveloper.reportbook.features.home.epoxy.models

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.kotlinsample.helpers.ViewBindingEpoxyModelWithHolder
import com.ouvrirdeveloper.reportbook.R
import com.ouvrirdeveloper.reportbook.databinding.EpoxyItemContentHeaderBinding


@EpoxyModelClass(layout = R.layout.epoxy_item_content_header)
abstract class ContentHeaderViewBindingEpoxyHolder :
    ViewBindingEpoxyModelWithHolder<EpoxyItemContentHeaderBinding>() {
    @EpoxyAttribute
    lateinit var title: String


    override fun EpoxyItemContentHeaderBinding.bind() {
        tvTitle.text = this@ContentHeaderViewBindingEpoxyHolder.title
    }

    override fun EpoxyItemContentHeaderBinding.unbind() {
    }

}