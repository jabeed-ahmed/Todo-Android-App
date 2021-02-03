package com.orion.todoapp.network

import com.orion.todoapp.model.TaskData
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface ApiService {
    @GET(
        "v3/9b1d275f-3938-483b-94c5-45d4eda4dd54"
    )
    suspend fun fetchTasks(): ApiResponse<List<TaskData>>
}
