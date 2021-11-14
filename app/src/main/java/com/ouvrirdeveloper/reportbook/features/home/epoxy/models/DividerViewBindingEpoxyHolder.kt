package com.ouvrirdeveloper.reportbook.features.home.epoxy.models

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.kotlinsample.helpers.ViewBindingEpoxyModelWithHolder
import com.ouvrirdeveloper.basearc.core.extension.asColor
import com.ouvrirdeveloper.reportbook.R
import com.ouvrirdeveloper.reportbook.databinding.EpoxyItemDividerBinding


@EpoxyModelClass(layout = R.layout.epoxy_item_divider)
abstract class DividerViewBindingEpoxyHolder :
    ViewBindingEpoxyModelWithHolder<EpoxyItemDividerBinding>() {

    @EpoxyAttribute
    var color: Int = R.color.secondaryDarkColor

    @EpoxyAttribute
    var heightIndp: Int = 1

    override fun EpoxyItemDividerBinding.bind() {
         viewBg.setBackgroundColor( color.asColor(context = root.context))
    }

}