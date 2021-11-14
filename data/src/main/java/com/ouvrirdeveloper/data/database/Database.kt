package com.ouvrirdeveloper.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ouvrirdeveloper.data.database.converters.DateConverter
import com.ouvrirdeveloper.data.database.dao.PendingTaskDao
import com.ouvrirdeveloper.data.models.entities.PendingTaskEntity

@TypeConverters(
    DateConverter::class
)
@Database(
    entities = [PendingTaskEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getPendingTaskDao(): PendingTaskDao

}