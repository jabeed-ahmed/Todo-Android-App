package com.orion.todoapp.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class TaskResult(
    val message: String,
    val result: Boolean
) : Parcelable
