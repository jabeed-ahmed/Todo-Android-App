package com.orion.todoapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.orion.todoapp.R
import com.orion.todoapp.databinding.ItemTodoBinding
import com.orion.todoapp.model.TaskData

class TasksAdapter : RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {

    private val items = mutableListOf<TaskData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemTodoBinding>(
            inflater,
            R.layout.item_todo,
            parent,
            false
        )

        return TaskViewHolder(binding).apply {
            binding.root.setOnClickListener { view ->
                val position =
                    adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                        ?: return@setOnClickListener
            }
        }
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.binding.apply {
            task = items[position]
            executePendingBindings()
        }
    }

    fun updateTaskList(tasks: List<TaskData>?) {
        items.clear()
        items.addAll(tasks!!)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    class TaskViewHolder(val binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root)
}