package com.orion.todoapp.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.orion.todoapp.model.Task
import com.orion.todoapp.model.TaskData

@Database(
    entities = [TaskData::class],
    version = 1, exportSchema = true
)
@TypeConverters(value = [TaskDetailsConverter::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDAO(): TaskDAO
}