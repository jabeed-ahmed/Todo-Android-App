package com.orion.todoapp.persistence

import androidx.room.*
import com.orion.todoapp.model.TaskData
import com.orion.todoapp.model.TaskItem

@Dao
interface TaskDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTaskDataList(tasks: List<TaskData>)

    @Query("SELECT * FROM TaskData WHERE id = :id_")
    fun getTaskData(id_: Long): TaskItem

    @Query("SELECT * FROM TaskData")
    fun getTaskDataList(): List<TaskData>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTaskData(task: TaskData)

    @Query("DELETE FROM TaskData Where id = :id_")
    fun deleteTaskData(id_: Long)
}
