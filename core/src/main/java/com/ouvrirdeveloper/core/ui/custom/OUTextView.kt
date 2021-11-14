package com.ouvrirdeveloper.core.ui.custom


import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.ouvrirdeveloper.basearc.core.extension.asColor
import com.ouvrirdeveloper.core.R


class OUTextView : ConstraintLayout {
    private lateinit var view: View

    init {
        view = LayoutInflater.from(context).inflate(R.layout.ou_textview, this, false)
        val set = ConstraintSet()
        addView(view)
        set.clone(this)
        set.match(view, this)
        setBackgroundColor(R.color.primaryColor.asColor(context))
    }

    constructor(context: Context) : super(context) {
        initView(context, null, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context, attrs, null)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        initView(context, attrs, defStyle)
    }

    private fun initView(context: Context, attrs: AttributeSet?, defStyle: Int?) {

        val a = context.obtainStyledAttributes(
            attrs,
            R.styleable.OUTextView
        )
        /*view.findViewById<AppCompatTextView>(R.id.tv)?.apply {
            val outext = a.getString(R.styleable.OUTextView_ou_text)
            val outitle = a.getString(R.styleable.OUTextView_ou_title)
            val background = a.getColor(R.styleable.OUTextView_android_background, R.color.white.asColor(context))
            if (outitle.isNullOrEmpty()) {
                text = outext
            } else {
                text = outitle
            }
            setBackgroundColor(background)
        }*/

    }

    fun ConstraintSet.match(view: View, parentView: View) {
        this.connect(view.id, ConstraintSet.TOP, parentView.id, ConstraintSet.TOP)
        this.connect(view.id, ConstraintSet.START, parentView.id, ConstraintSet.START)
        this.connect(view.id, ConstraintSet.END, parentView.id, ConstraintSet.END)
        this.connect(view.id, ConstraintSet.BOTTOM, parentView.id, ConstraintSet.BOTTOM)
    }
    fun getOutextView() =  view.findViewById<AppCompatTextView>(R.id.tv)
}