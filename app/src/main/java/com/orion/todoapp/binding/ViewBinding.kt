package com.orion.todoapp.binding

import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.orion.todoapp.extensions.gone

object ViewBinding {
    @JvmStatic
    @BindingAdapter("gone")
    fun bindGone(view: ProgressBar, shouldBeGone: Boolean?) {
        if (shouldBeGone == true) {
            view.gone(true)
        } else {
            view.gone(false)
        }
    }

}