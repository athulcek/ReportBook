package com.ouvrirdeveloper.domain.usecases

import com.ouvrirdeveloper.domain.models.PendingTask
import com.ouvrirdeveloper.domain.models.Resource
import com.ouvrirdeveloper.domain.models.Status
import com.ouvrirdeveloper.domain.models.User
import com.ouvrirdeveloper.domain.repositories.UserRepository
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

    fun isKeepMeLoggedIn() = userRepository.isKeepMeLoggedIn()


    fun onLogout() {
        userRepository.logOut()
        //  userRepository.saveUserProfile(null)
    }

    suspend fun getUserFromDisk() = userRepository.getUserFromDisk()
    fun saveToDisk(userId: String, password: String, companyId: Int)=userRepository.saveToDisk(userId,password,companyId)

}

