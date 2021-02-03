package com.orion.todoapp.model

class Task : ArrayList<TaskItem>()

data class TaskItem(
    val note: String,
    val title: String
)

