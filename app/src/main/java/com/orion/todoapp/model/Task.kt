package com.orion.todoapp.model

class Task : ArrayList<TaskItem>()

data class TaskItem(
    val id: Long,
    val note: String,
    val title: String
)

