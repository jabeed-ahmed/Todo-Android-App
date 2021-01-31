package com.orion.todoapp.extensions

import android.view.View

/** makes gone a view. */
fun View.gone(shouldBeGone: Boolean) {
    if (shouldBeGone) visibility = View.GONE
    else visible()
}

/** makes visible a view. */
fun View.visible() {
    visibility = View.VISIBLE
}