package com.orion.todoapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class TaskData(
    @PrimaryKey val id: Long,
    val title: String,
    val note: String,
) : Parcelable
