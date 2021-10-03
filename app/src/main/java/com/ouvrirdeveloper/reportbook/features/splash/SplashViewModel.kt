package com.ouvrirdeveloper.myreport.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ouvrirdeveloper.basearc.ui.base.BaseViewModel
import com.ouvrirdeveloper.domain.models.Resource
import com.ouvrirdeveloper.domain.models.User
import com.ouvrirdeveloper.domain.models.PendingTask
import com.ouvrirdeveloper.domain.usecases.UserUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SplashViewModel(private val userUseCase: UserUseCase) : BaseViewModel() {

    private val _userInfo = MutableLiveData<User?>()
    private val _loginDetail = MutableLiveData<Resource<User>>()
    val loginDetail: LiveData<Resource<User>>
        get() = _loginDetail
    private val _pendingTasks = MutableLiveData<Resource<List<PendingTask>>>()
    val pendingTasks: LiveData<Resource<List<PendingTask>>>
        get() = _pendingTasks
    val userInfo: LiveData<User?>
        get() = _userInfo

    init {
        viewModelScope.launch {

        }
    }

    fun getPendingTasks() {
        runAsyncTask {
            userUseCase.viewPendingTaskList(1).collect {
                _pendingTasks.value = it

            }
        }
    }

}