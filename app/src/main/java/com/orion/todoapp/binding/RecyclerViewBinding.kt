package com.orion.todoapp.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.orion.todoapp.model.TaskData
import com.orion.todoapp.model.TaskItem
import com.orion.todoapp.view.adapter.TasksAdapter
import com.skydoves.whatif.whatIfNotNull

@BindingAdapter("taskAdapter")
fun bindAdapter(view: RecyclerView, baseAdapter: RecyclerView.Adapter<*>) {
    view.adapter = baseAdapter
}

/* ITEM TASK RECYCLERVIEW BINDING */
@BindingAdapter("adapterTaskList")
fun bindAdapterTask(view: RecyclerView, tasks: List<TaskData>?) {
    tasks.whatIfNotNull {
        (view.adapter as? TasksAdapter)?.updateTaskList(it)
    }
}