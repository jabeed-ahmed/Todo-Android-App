package com.orion.todoapp.binding

import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.orion.todoapp.extensions.gone
import com.orion.todoapp.model.TaskResult
import com.skydoves.whatif.whatIfNotNull

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

    @JvmStatic
    @BindingAdapter("displayTitle")
    fun bindDisplayTitle(view: MaterialTextView, taskTitle: String?) {
        view.text = "Add New Task"
    }

    @JvmStatic
    @BindingAdapter("message")
    fun bindMessage(view: MaterialButton, result: TaskResult?) {
        result.whatIfNotNull {
            if (it.result) {
                Toast.makeText(view.context, it.message + "" + it.result, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(view.context, it.message + "" + it.result, Toast.LENGTH_LONG).show()
            }
        }
    }

}