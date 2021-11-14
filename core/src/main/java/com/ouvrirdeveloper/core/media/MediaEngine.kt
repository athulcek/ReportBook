package com.ouvrirdeveloper.basearc.helper.media

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView

interface MediaEngine {

    /**
     * Load thumbnail of a static image resource.
     *
     * @param resize      Desired size of the origin image
     * @param placeholder Placeholder drawable when image is not loaded yet
     * @param imageView   ImageView widget
     * @param uri         Uri of the loaded image
     */
    fun loadThumbnail(
        resize: Int,
        placeholder: Drawable,
        imageView: ImageView,
        uri: Uri
    )


}