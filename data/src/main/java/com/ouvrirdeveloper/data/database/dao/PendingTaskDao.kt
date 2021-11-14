package com.ouvrirdeveloper.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ouvrirdeveloper.core.model.BaseDao
import com.ouvrirdeveloper.data.models.entities.PendingTaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PendingTaskDao : BaseDao<PendingTaskEntity> {

    @Insert
    fun insertList(data: List<PendingTaskEntity>)

    @Query("Select * from PendingTask")
    fun getPendingTasksDb(): Flow<List<PendingTaskEntity>>

}