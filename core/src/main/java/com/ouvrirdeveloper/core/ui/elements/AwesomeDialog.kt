package com.ouvrirdeveloper.core.ui.elements

import android.app.Activity
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.RelativeLayout
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import com.ouvrirdeveloper.core.R
import com.ouvrirdeveloper.core.databinding.AwesomeDilaogBinding


class AwesomeDialog {

    /***
     * Positions For Alert Dialog
     * */
    enum class POSITIONS {
        CENTER, BOTTOM
    }

    companion object {

        private lateinit var layoutInflater: LayoutInflater
        lateinit var binding: AwesomeDilaogBinding

        /***
         * core method For Alert Dialog
         * */
        fun build(
            context: Activity
        ): AlertDialog {
            layoutInflater = LayoutInflater.from(context)
            binding = AwesomeDilaogBinding.inflate(layoutInflater)
            val alertDialog =
                AlertDialog.Builder(
                    context, R.style.full_screen_dialog
                )
                    .setView(binding.root)
            val alert: AlertDialog = alertDialog.create()
            // Let's start with animation work. We just need to create a style and use it here as follows.
            //Pop In and Pop Out Animation yet pending
//            alert.window?.attributes?.windowAnimations = R.style.SlidingDialogAnimation
            alert.show()
            return alert
        }
    }
}

/***
 * Title Properties For Alert Dialog
 * */
fun AlertDialog.title(
    title: String,
    fontStyle: Typeface? = null,
    titleColor: Int = 0
): AlertDialog {
    val tvtitle = AwesomeDialog.binding.title
    tvtitle.text = title.trim()
    if (fontStyle != null) {
        tvtitle.typeface = fontStyle
    }
    if (titleColor != 0) {
        tvtitle.setTextColor(titleColor)
    }
    tvtitle.show()
    return this
}

/***
 * Dialog Background properties For Alert Dialog
 * */
fun AlertDialog.background(
    dialogBackgroundColor: Int? = null
): AlertDialog {
    if (dialogBackgroundColor != null) {
        this.findViewById<ConstraintLayout>(R.id.mainLayout)
            ?.setBackgroundResource(dialogBackgroundColor)
    }
    return this
}

/***
 * Positions of Alert Dialog
 * */
fun AlertDialog.position(
    position: AwesomeDialog.POSITIONS = AwesomeDialog.POSITIONS.BOTTOM
): AlertDialog {
    val mainLayout = AwesomeDialog.binding.mainLayout
    val layoutParams = mainLayout.layoutParams as RelativeLayout.LayoutParams
    if (position == AwesomeDialog.POSITIONS.CENTER) {
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)
    } else if (position == AwesomeDialog.POSITIONS.BOTTOM) {
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)
    }
    mainLayout!!.layoutParams = layoutParams
    return this
}

/***
 * Sub Title or Body of Alert Dialog
 * */
fun AlertDialog.body(
    body: String,
    fontStyle: Typeface? = null,
    color: Int = 0
): AlertDialog {
    val subHeading = AwesomeDialog.binding.subHeading
    subHeading.text = body.trim()
    subHeading.show()
    if (fontStyle != null) {
        subHeading.typeface = fontStyle
    }
    if (color != 0) {
        subHeading.setTextColor(color)
    }
    return this
}

/***
 * Icon of  Alert Dialog
 * */
fun AlertDialog.icon(
    icon: Int,
    animateIcon: Boolean = false
): AlertDialog {
    val image = AwesomeDialog.binding.image
    image.setImageResource(icon)
    image.show()
    // Pulse Animation for Icon
    if (animateIcon) {
        val pulseAnimation = AnimationUtils.loadAnimation(context, R.anim.pulse)
        image.startAnimation(pulseAnimation)
    }
    return this
}

/***
 * onPositive Button Properties For Alert Dialog
 *
 * No Need to call dismiss(). It is calling already
 * */
fun AlertDialog.onPositive(
    text: String,
    buttonBackgroundColor: Int? = null,
    textColor: Int? = null,
    action: (() -> Unit)? = null
): AlertDialog {
    val yesButton = AwesomeDialog.binding.yesButton
    yesButton.show()
    if (buttonBackgroundColor != null) {
        yesButton.setBackgroundResource(buttonBackgroundColor)
    }
    if (textColor != null) {
        yesButton.setTextColor(textColor)
    }
    yesButton.text = text.trim()
    yesButton.setOnClickListener {
        action?.invoke()
        dismiss()
    }
    return this
}

/***
 * onNegative Button Properties For Alert Dialog
 *
 * No Need to call dismiss(). It is calling already
 * */
fun AlertDialog.onNegative(
    text: String,
    buttonBackgroundColor: Int? = null,
    textColor: Int? = null,
    action: (() -> Unit)? = null
): AlertDialog {
    val noButton = AwesomeDialog.binding.noButton
    noButton.show()
    noButton.text = text.trim()
    if (textColor != null) {
        noButton.setTextColor(textColor)
    }
    if (buttonBackgroundColor != null) {
        noButton.setBackgroundResource(buttonBackgroundColor)
    }
    noButton.setOnClickListener {
        action?.invoke()
        dismiss()
    }
    return this
}

private fun View.show() {
    this.visibility = View.VISIBLE
}