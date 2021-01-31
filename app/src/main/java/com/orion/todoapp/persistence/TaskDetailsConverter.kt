package com.orion.todoapp.persistence

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.orion.todoapp.model.TaskItem

class TaskDetailsConverter {
    @TypeConverter
    fun fromString(value: String): List<TaskItem>? {
        val listType = object : TypeToken<List<TaskItem>>() {}.type
        return Gson().fromJson<List<TaskItem>>(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<TaskItem>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}