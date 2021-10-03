package com.ouvrirdeveloper.core.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.view.View
import com.ouvrirdeveloper.basearc.ViewVisibility

object AnimationUtil {

    fun crossFadeViews(
        viewToShow: View,
        viewToHide: View,
        duration: Long,
        @ViewVisibility hidingViewVisibility: Int = View.GONE
    ) {
        fadeInView(viewToShow, duration)

        fadeOutView(viewToHide, duration, hidingViewVisibility)
    }

    fun fadeOutView(
        viewToHide: View,
        duration: Long = 500,
        @ViewVisibility hidingViewVisibility: Int = View.GONE
    ) {
        viewToHide.animate().cancel()
        viewToHide.animate()
            .alpha(0f)
            .setDuration(duration)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    viewToHide.visibility = hidingViewVisibility
                    viewToHide.alpha = 1f
                }
            })
    }

    fun fadeInView(viewToShow: View, duration: Long = 500) {
        viewToShow.animate().cancel()
        viewToShow.apply {
            // Set the content view to 0% opacity but visible, so that it is visible
            // (but fully transparent) during the animation.
            alpha = 0f
            visibility = View.VISIBLE

            // Animate the content view to 100% opacity, and clear any animation
            // listener set on the view.
            animate()
                .alpha(1f)
                .setDuration(duration)
                .setListener(null)
        }
    }

    fun slideOutRight(
        view: View,
        fromX: Float,
        toX: Float,
        duration: Long = 1000,
        listener: AnimatorListenerAdapter? = null
    ) {
        val anim: ObjectAnimator =
            ObjectAnimator.ofFloat(
                view,
                "translationX",
                fromX,
                toX
            )
        anim.duration = duration
        listener?.let {
            anim.addListener(it)
        }
        anim.start()
    }
}