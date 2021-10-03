package com.ouvrirdeveloper.data.di

import android.content.Context
import androidx.room.Room
import com.bushnell.golf.data.database.AppDatabase
import com.bushnell.golf.data.database.dao.PendingTaskDao
import com.bushnell.golf.data.datasource.FileDataSource
import com.bushnell.golf.domain.repositories.UserRepository
import com.ouvrirdeveloper.data.api.UserApiService
import com.ouvrirdeveloper.data.datasource.TaskDataSource
import com.ouvrirdeveloper.data.datasource.UserDataSource
import com.ouvrirdeveloper.data.helper.PreferenceHelper
import com.ouvrirdeveloper.data.jobs.WorkManagerScheduler
import com.ouvrirdeveloper.data.repositoriesImpl.TaskRepositoryImpl
import com.ouvrirdeveloper.data.repositoriesImpl.UserRepositoryImpl
import com.ouvrirdeveloper.domain.repositories.TaskRepository
import org.koin.dsl.module

val DataProviderModule = module {

    single { createRoomDatabase(get()) }
    single { createUserRepository(get(), get()) }
    single { createTaskRepository(get()) }
    single { createUserDataSource(get(), get()) }
    single { createTaskDataSource(get(), get()) }
    single { createPreferenceHelper(get()) }
    single { createPendingTaskDao(get()) }
    single { createFileDataSource(get()) }
    single { createWorkManagerScheduler(get()) }
}

fun createPreferenceHelper(context: Context) = PreferenceHelper(context)

fun createUserDataSource(userApiService: UserApiService, pendingTaskDao: PendingTaskDao) =
    UserDataSource(userApiService, pendingTaskDao)

fun createTaskDataSource(userApiService: UserApiService, pendingTaskDao: PendingTaskDao) =
    TaskDataSource(userApiService, pendingTaskDao)

fun createUserRepository(
    userDataSource: UserDataSource,
    preferenceHelper: PreferenceHelper
): UserRepository =
    UserRepositoryImpl(userDataSource, preferenceHelper)


fun createTaskRepository(
    taskDataSource: TaskDataSource
): TaskRepository =
    TaskRepositoryImpl(taskDataSource)

fun createFileDataSource(context: Context) = FileDataSource(context)
fun createPendingTaskDao(appDatabase: AppDatabase) = appDatabase.getPendingTaskDao()
fun createWorkManagerScheduler(context: Context): WorkManagerScheduler =
    WorkManagerScheduler(context)

fun createRoomDatabase(context: Context): AppDatabase {
    return Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "app_database"
    ).build()
}