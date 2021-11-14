package com.ouvrirdeveloper.core.model

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Update

interface BaseDao<T> {

    @Insert(onConflict = IGNORE)
    fun insert(t: T): Long

    @Update(onConflict = REPLACE)
    fun update(t: T)

    @Delete
    fun delete(t: T)
}