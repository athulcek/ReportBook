package com.ouvrirdeveloper.reportbook.features.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ouvrirdeveloper.basearc.ui.base.BaseViewModel
import com.ouvrirdeveloper.core.utils.permitDiskReads
import com.ouvrirdeveloper.domain.models.PendingTask
import com.ouvrirdeveloper.domain.models.Resource
import com.ouvrirdeveloper.domain.models.User
import com.ouvrirdeveloper.domain.usecases.TaskUseCase
import com.ouvrirdeveloper.domain.usecases.UserUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class SplashViewModel(private val userUseCase: UserUseCase,
private val taskUseCase: TaskUseCase) : BaseViewModel() {

    private val _userInfo = MutableLiveData<User?>()

    private val _loginDetail = MutableLiveData<Resource<User?>>()
    val loginDetail: LiveData<Resource<User?>>
        get() = _loginDetail

    private val _pendingTasks = MutableLiveData<Resource<List<PendingTask>?>>()
    val pendingTasks: LiveData<Resource<List<PendingTask>?>>
        get() = _pendingTasks
    val userInfo: LiveData<User?>
        get() = _userInfo

    init {
        viewModelScope.launch {

        }
    }

    fun getPendingTasks() {
        runOnMain {
            taskUseCase.getPendingTaskList(1).collect {
                _pendingTasks.value = it

            }
        }
    }

    fun doLogin() {
        permitDiskReads {
        runIfNotInProgress {
            runOnMain {
                 userUseCase.getUserFromDisk()?.apply {
                    userUseCase.login(
                        User(
                            userId,
                            password,
                            companyId
                        ), true
                    )
                        .onStart {
                            _loginDetail.value = Resource.loading()
                        }.collect {
                            delay(2000)
                            _loginDetail.value = it
                        }
                }

            }
            }
        }
    }

   suspend fun isLoggedIn() =  userUseCase.getUserFromDisk() != null

}