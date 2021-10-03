package com.ouvrirdeveloper.domain.usecases

import com.bushnell.golf.domain.repositories.UserRepository
import com.ouvrirdeveloper.domain.models.PendingTask
import com.ouvrirdeveloper.domain.models.Resource
import com.ouvrirdeveloper.domain.models.Status
import com.ouvrirdeveloper.domain.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class UserUseCase(private val userRepository: UserRepository) : BaseUseCase() {


    private var user: User? = null

    fun setUser(user: User?) {
        this.user = user
    }

    fun getUser(): User? {
        return user
    }

    fun login(loginDetails: User, isKeepMeLoggedIn: Boolean) = userRepository.login(loginDetails)

    fun viewPendingTaskList(loadType: Int): Flow<Resource<List<PendingTask>>> {
        return flow {
            userRepository.viewPendingTaskList(loadType).collect {
                if (it.status == Status.SUCCESS && it.data != null) {
                    runAsyncTask {
                        userRepository.updateDbwithPendingTasks(it.data)
                    }
                }
                emit(it)
            }
        }
    }


    fun isKeepMeLoggedIn() = userRepository.isKeepMeLoggedIn()


    fun onLogout() {
        userRepository.logOut()
        //  userRepository.saveUserProfile(null)
    }

}

