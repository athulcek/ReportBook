package com.ouvrirdeveloper.basearc.helper.media

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import coil.load
import coil.transform.CircleCropTransformation

class CoilEngine : MediaEngine {
    override fun loadThumbnail(
        resize: Int,
        placeholder: Drawable,
        imageView: ImageView,
        uri: Uri
    ) {
        imageView.load(uri) {
            placeholder(placeholder)
            crossfade(true)
            transformations(CircleCropTransformation())
            size(resize)
        }

    }
}