package com.orion.todoapp.model

import android.os.Parcelable
import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class TaskData(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val title: String,
    val note: String,
) : Parcelable
