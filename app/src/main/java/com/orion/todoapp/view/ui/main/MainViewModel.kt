package com.orion.todoapp.view.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.engine.Resource
import com.orion.todoapp.base.LiveCoroutinesViewModel
import com.orion.todoapp.model.TaskData
import com.orion.todoapp.model.TaskItem
import com.orion.todoapp.model.TaskResult
import com.orion.todoapp.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class MainViewModel constructor(
    private val mainRepository: MainRepository
) : LiveCoroutinesViewModel() {

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _dataList: MutableLiveData<List<TaskData>> = MutableLiveData()
    var taskListLiveData: LiveData<List<TaskData>> = _dataList

    private val _taskHeader: MutableLiveData<String> = MutableLiveData()
    val taskHeader: LiveData<String> get() = _taskHeader

    private var _dialogResult: MutableLiveData<TaskResult> = MutableLiveData()
    var dialogResult: LiveData<TaskResult> = _dialogResult

    var title = MutableLiveData<String>()
    var description = MutableLiveData<String>()

    private val task = MutableLiveData<Resource<TaskData>>()

    init {
        Timber.d("INIT MAIN VIEW MODEL")
        _isLoading.postValue(true)
        viewModelScope.launch {
            getTasks().collect { list ->
                _dataList.postValue(list)
            }
        }
    }

    private suspend fun getTasks(): Flow<List<TaskData>> {
        return withContext(Dispatchers.IO) {
            mainRepository.loadTasks(
                onSuccess = {
                    _isLoading.postValue(false)
                    _dataList.postValue(it)
                    Timber.d("data " + it.size)
                },
                onError = {
                    Timber.d("Error Occurred!")
                    _isLoading.postValue(false)
                })
        }
    }

    fun postTask(item: TaskItem) {
        Timber.d("Item received!$item")
        _isLoading.postValue(true)
        val todo = TaskData(null, item.title, item.note)
        mainRepository.postTask(todo)
        viewModelScope.launch {
            getTasks().collect { list ->
                _dataList.postValue(list)
            }
        }
    }
}