package com.ouvrirdeveloper.reportbook.features.home.epoxy.models

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.ModelView
import com.ouvrirdeveloper.basearc.core.extension.asColor
import com.ouvrirdeveloper.reportbook.R

@ModelView( autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT,saveViewState = true)
class VerticalGridCarousel(context: Context) : Carousel(context) {

    init {
        //setBackgroundColor(R.color.secondaryColor.asColor(context))
        }


    override fun createLayoutManager(): LayoutManager {
        return GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
    }

}

/*
// usage in fragment
verticalGridCarousel {
    id("Carousel")
    models(texts.map { text ->
        CustomTextModel_().apply {
            id(text)
            styleBuilder {
                it.text(text)
                it.textSizeDp(30)
                it.layoutWidth(ViewGroup.LayoutParams.MATCH_PARENT)
                it.layoutHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
            }
        }
    })
}*/
