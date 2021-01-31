package com.orion.todoapp.repository

import com.orion.todoapp.model.TaskData
import com.orion.todoapp.network.ApiService
import com.orion.todoapp.persistence.TaskDAO
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

class MainRepository constructor(
    private val apiService: ApiService,
    private val taskDAO: TaskDAO
) : Repository {
    init {
        Timber.d("Injection MainRepository")
    }

    fun loadTasks(
        onSuccess: (List<TaskData>) -> Unit,
        onError: (String) -> Unit
    ) = flow {
        val tasks = taskDAO.getTaskDataList()
        if (tasks.isEmpty()) {
            // Request API network call asynchronously.
            apiService.fetchTasks().apply {
                // Handle the case when the API request gets a success response.
                this.suspendOnSuccess {
                    data.whatIfNotNull {
                        emit(it)
                        onSuccess(it)
                        taskDAO.insertTaskDataList(it)
                    }
                }
                    .onError {
                        onError(message())
                    }
                    .onException {
                        onError(message())
                    }
            }
        } else {
            onSuccess(tasks)
        }
    }.flowOn(Dispatchers.IO)
}
