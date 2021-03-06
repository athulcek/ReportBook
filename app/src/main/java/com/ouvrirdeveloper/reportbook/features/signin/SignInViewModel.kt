package com.ouvrirdeveloper.reportbook.features.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ouvrirdeveloper.basearc.core.extension.isValidEmail
import com.ouvrirdeveloper.basearc.ui.base.BaseViewModel
import com.ouvrirdeveloper.domain.models.Resource
import com.ouvrirdeveloper.domain.models.User
import com.ouvrirdeveloper.domain.models.PendingTask
import com.ouvrirdeveloper.domain.usecases.UserUseCase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart

class SignInViewModel(private val userUseCase: UserUseCase) : BaseViewModel() {

    companion object {
        const val ignoreValidationMessage = -1
        const val EmailRequired = 1
        const val EmailInvalid = 2
        const val PasswordRequired = 3
    }

    val userId = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val companyId = MutableLiveData<String>()

    val isKeepMeLoggedIn = MutableLiveData(userUseCase.isKeepMeLoggedIn())

    private val _loginDetail = MutableLiveData<Resource<User?>>()
    val loginDetail: LiveData<Resource<User?>>
        get() = _loginDetail

      val _pendingTasks = MutableLiveData<Resource<List<PendingTask>>>()

    val pendingTasks: LiveData<Resource<List<PendingTask>>>
        get() = _pendingTasks

    private val _validation = MutableLiveData<Int>()
    val validation: LiveData<Int>
        get() = _validation

    init {
        userId.value = "admin"
        password.value = "Admin@123"

    }


    @InternalCoroutinesApi
    @Suppress("EXPERIMENTAL_API_USAGE")
    fun doLogIn() {
        if (!validateLoginDetails()) {
            return
        }
        runIfNotInProgress {
            runOnMain {
                userUseCase.login(
                    User(
                        userId.value!!,
                        password.value!!
                    ), isKeepMeLoggedIn.value ?: false
                )
                    .onStart {
                        _loginDetail.value = Resource.loading()
                    }.collect {
                        _loginDetail.value = it
                    }

            }
        }
    }

    fun validateEmail(isSubmitClicked: Boolean = false): Boolean {
        if (userId.value.isNullOrEmpty()) {
            _validation.value = if (isSubmitClicked) EmailRequired else ignoreValidationMessage
            return false
        }
        if (userId.value?.isValidEmail()?.not() == false) {
            _validation.value = if (isSubmitClicked) EmailInvalid else ignoreValidationMessage
            return false
        }
        return true
    }

    fun validatePassword(): Boolean {
        return if (password.value.isNullOrEmpty()) {
            _validation.value = PasswordRequired
            false
        } else true
    }

    private fun validateLoginDetails(): Boolean {
        val isValid = validateEmail(true)
        return validatePassword() && isValid
    }

    fun getPendingTasks() {
        runOnMain {
            userUseCase.viewPendingTaskList(1)
                .onStart {
                    _pendingTasks.value = Resource.loading()
                }
                .collect {
                _pendingTasks.value = it
            }
        }
    }
}