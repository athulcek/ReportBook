package com.ouvrirdeveloper.domain.di

import com.ouvrirdeveloper.domain.repositories.UserRepository
import com.ouvrirdeveloper.domain.repositories.TaskRepository
import com.ouvrirdeveloper.domain.usecases.TaskUseCase
import com.ouvrirdeveloper.domain.usecases.UserUseCase
import org.koin.dsl.module

val DomainProviderModule = module {

    single { createUserUsecase(get()) }
    single { createUserTaskUseCase(get()) }
}

fun createUserUsecase(userRepository: UserRepository) = UserUseCase(userRepository)
fun createUserTaskUseCase(taskRepository: TaskRepository) = TaskUseCase(taskRepository)
