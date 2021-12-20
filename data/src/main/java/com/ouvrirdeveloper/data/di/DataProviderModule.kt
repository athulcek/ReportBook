package com.ouvrirdeveloper.data.di

import android.content.Context
import androidx.room.Room
import com.ouvrirdeveloper.core.utils.PrefUtil
import com.ouvrirdeveloper.data.api.UserApiService
import com.ouvrirdeveloper.data.database.AppDatabase
import com.ouvrirdeveloper.data.database.dao.PendingTaskDao
import com.ouvrirdeveloper.data.datasource.FileDataSource
import com.ouvrirdeveloper.data.datasource.TaskDataSource
import com.ouvrirdeveloper.data.datasource.UserDataSource
import com.ouvrirdeveloper.data.helper.PreferenceHelper
import com.ouvrirdeveloper.data.jobs.WorkManagerScheduler
import com.ouvrirdeveloper.data.repositoriesImpl.TaskRepositoryImpl
import com.ouvrirdeveloper.data.repositoriesImpl.UserRepositoryImpl
import com.ouvrirdeveloper.domain.repositories.TaskRepository
import com.ouvrirdeveloper.domain.repositories.UserRepository
import com.ouvrirdeveloper.reportbook.appstate.AppState
import org.koin.dsl.module

val DataProviderModule = module {

    single { createRoomDatabase(get()) }
    single { createUserRepository(get(), get()) }
    single { createTaskRepository(get(),get()) }
    single { createUserDataSource(get(), get()) }
    single { createTaskDataSource(get(), get()) }
    single { createPreferenceHelper(get()) }
    single { createPrefHelper(get()) }
    single { createPendingTaskDao(get()) }
    single { createFileDataSource(get()) }
    single { createAppState() }
    single { createWorkManagerScheduler(get()) }
}

fun createPreferenceHelper(context: Context) = PreferenceHelper(context)
fun createPrefHelper(context: Context) = PrefUtil.init(context)
fun createAppState() = AppState()

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
    taskDataSource: TaskDataSource,
    preferenceHelper: PreferenceHelper
): TaskRepository =
    TaskRepositoryImpl(taskDataSource,preferenceHelper)

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