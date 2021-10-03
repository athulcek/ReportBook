package com.ouvrirdeveloper.data.models.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = "PendingTask",
    indices = [Index(value = arrayOf("docsrchcode"), unique = true)]
)
data class PendingTaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val docsrchcode: String,
    val doctype: String,
    val totalcount: Long
) : BaseTable() {

}