package com.orion.todoapp.binding

import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class ToolbarConfiguration : BaseObservable() {
    // Bindable getters
    // Fields
    @get:Bindable
    var title: String? = null
    private var listener: View.OnClickListener? = null

    @Bindable
    fun getListener(): View.OnClickListener? {
        return listener
    }

    // Set content using this method. NotifyChange() will trigger a UI update.
    fun setConfiguration(title: String?, listener: View.OnClickListener?) {
        this.title = title
        this.listener = listener
        notifyChange()
    }
}