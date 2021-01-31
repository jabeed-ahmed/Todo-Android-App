package com.orion.todoapp.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.skydoves.sandwich.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers

abstract class LiveCoroutinesViewModel : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    inline fun <T> launchOnViewModelScope(crossinline block: suspend () -> LiveData<T>): LiveData<T> {
        return liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emitSource(block())
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}