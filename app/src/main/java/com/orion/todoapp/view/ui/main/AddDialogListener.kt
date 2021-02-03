package com.orion.todoapp.view.ui.main

import com.orion.todoapp.model.TaskItem

interface AddDialogListener {
    fun onAddButtonClicked(item: TaskItem)
}