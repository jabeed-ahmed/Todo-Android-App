package com.orion.todoapp.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.orion.todoapp.R
import com.orion.todoapp.databinding.ItemTodoBinding
import com.orion.todoapp.model.TaskData
import com.orion.todoapp.view.ui.main.MainActivity

class TasksAdapter(mainActivity: MainActivity) : RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {

    private val items = mutableListOf<TaskData>()
    private var mOnItemClickListener: OnItemClickListener = mainActivity

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

        holder.binding.imgDelete.setOnClickListener {
            mOnItemClickListener.onItemClick(items[position])

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

    interface OnItemClickListener {
        fun onItemClick(item: TaskData)
    }
}