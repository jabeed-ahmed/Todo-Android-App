package com.orion.todoapp.view.ui.main

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.orion.todoapp.databinding.DialogAddTaskBinding
import com.orion.todoapp.model.TaskItem
import kotlinx.android.synthetic.main.dialog_add_task.*

class AddTaskItemDialog(
    context: Context,
    var dialogListener: AddDialogListener
) :
    AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: DialogAddTaskBinding = DialogAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvAdd.setOnClickListener {
            val title = etTitle.text.toString()
            val description = etDescription.text.toString()

            if (title.isEmpty() || description.isEmpty()) {
                Toast.makeText(context, "Please enter required fields!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val item = TaskItem(title, description)
            dialogListener.onAddButtonClicked(item)
            dismiss()
        }

        binding.tvCancel.setOnClickListener {
            cancel()
        }
    }
}