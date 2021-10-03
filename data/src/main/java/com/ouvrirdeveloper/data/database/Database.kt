package com.bushnell.golf.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bushnell.golf.data.database.converters.DateConverter
import com.bushnell.golf.data.database.dao.PendingTaskDao
import com.ouvrirdeveloper.data.models.entities.PendingTaskEntity

@TypeConverters(
    DateConverter::class
)
@Database(
    entities = [PendingTaskEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getPendingTaskDao(): PendingTaskDao

}