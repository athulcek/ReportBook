package com.ouvrirdeveloper.reportbook.features.home.epoxy.models

import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.kotlinsample.helpers.ViewBindingEpoxyModelWithHolder
import com.ouvrirdeveloper.basearc.core.extension.asColor
import com.ouvrirdeveloper.core.extensions.setNullOnclickCombained
import com.ouvrirdeveloper.core.extensions.setOnclickCombained
import com.ouvrirdeveloper.reportbook.R
import com.ouvrirdeveloper.reportbook.databinding.EpoxyItemContentBinding


@EpoxyModelClass(layout = R.layout.epoxy_item_content)
abstract class ContentViewBindingEpoxyHolder :
    ViewBindingEpoxyModelWithHolder<EpoxyItemContentBinding>() {
    @EpoxyAttribute
    lateinit var title: String

    @EpoxyAttribute
    var count: Long = 0

    @EpoxyAttribute
    @ColorRes
    var color: Int = 0

    @EpoxyAttribute
    lateinit var context: Context

    @EpoxyAttribute
    var width: Int = 0

    @EpoxyAttribute
    lateinit var onClick: ((String) -> Unit)

    override fun EpoxyItemContentBinding.bind() {
        tvTitle.text = this@ContentViewBindingEpoxyHolder.title

        tvCount.text = this@ContentViewBindingEpoxyHolder.count.toString()
        tvTitle.text = this@ContentViewBindingEpoxyHolder.title

        setOnclickCombained(arrayOf(parentCard)) {
            onClick.invoke(title)
        }
        viewBg.backgroundTintList = ContextCompat.getColorStateList(context, color)
        tvTitle.setTextColor(this@ContentViewBindingEpoxyHolder.color.asColor(context))
/*

        var background = ResourcesCompat.getDrawable(
            context.resources,
            R.drawable.card_gradient,
            null
        ) as GradientDrawable
        var colorList =
            IntArray(10) { getWhiteColor() } // Create an integer array of all white values
        background.mutate() // Mutate the drawable so changes don't affect every other drawable
        // background.setGradientCenter(0.1F, 0.5F) // Reset the center of the gradient (default is 0.5F, 0.5F)) - Only works for radial and sweep types

        // Conditionaly change the last color in the integer array
        *//* when (word?.partOfSpeech) {
             "noun" -> colorList[colorList.size-1] = Color.argb(255, 66, 133, 244) // Blue
             "verb" -> colorList[colorList.size-1] = Color.argb(255, 15, 157, 88) // Green
             "adjective" -> colorList[colorList.size-1] = Color.argb(255, 219, 68, 55) // Red
             else -> colorList[colorList.size-1] = Color.argb(255, 244, 180, 0) // Yellow
         }*//*
        colorList[colorList.size - 1] = color

        background.setColors(colorList) // Change the drawable's gradient to your new color list
        viewBg.background = background*/
    }

    private fun getWhiteColor(): Int {
        return R.color.background.asColor(context)
    }

    override fun EpoxyItemContentBinding.unbind() {
        setNullOnclickCombained(arrayOf(viewBg, tvTitle, tvCount, parentCard)) {

        }
    }

}