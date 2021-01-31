package com.orion.todoapp.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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
}
