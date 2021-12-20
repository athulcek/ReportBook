package com.ouvrirdeveloper.core.extensions

import android.view.View
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer


fun Spinner.setCurrentSelection(selectedItem: String): Boolean {
    for (index in 0 until this.adapter.count) {
        if (this.getItemAtPosition(index) == selectedItem) {
            this.setSelection(index)
            return true
        }
    }
    return false

}


inline fun <reified T : ViewDataBinding> bindings(view: View): Lazy<T> = lazy {
    DataBindingUtil.bind<T>(view)?.let { it }
        ?: throw IllegalAccessException("cannot find the matched view to layout")
}


fun setOnclickCombained(views: Array<View>, onClick: () -> Unit) {
    views.forEach {
        it.setOnClickListener {
            onClick.invoke()
        }
    }
}
fun setNullOnclickCombained(views: Array<View>, onClick: () -> Unit) {
    views.forEach {
        it.setOnClickListener(null)
    }
}

fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    observe(lifecycleOwner, object : Observer<T> {
        override fun onChanged(t: T?) {
            observer.onChanged(t)
            removeObserver(this)
        }
    })
}

inline fun View.gone() {
    if (visibility != View.GONE) visibility = View.GONE
}

inline fun View.makeVisible() {
    if (visibility != View.VISIBLE) visibility = View.VISIBLE
}

inline fun View.makeInvisible() {
    if (visibility != View.INVISIBLE) visibility = View.INVISIBLE
}