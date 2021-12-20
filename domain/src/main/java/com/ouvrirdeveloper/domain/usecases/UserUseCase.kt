package com.ouvrirdeveloper.domain.usecases

import com.ouvrirdeveloper.domain.models.RemoteConfigData
import com.ouvrirdeveloper.domain.models.User
import com.ouvrirdeveloper.domain.repositories.UserRepository

class UserUseCase(private val userRepository: UserRepository) : BaseUseCase() {

    fun login(loginDetails: User, isKeepMeLoggedIn: Boolean) = userRepository.login(loginDetails)

    fun isKeepMeLoggedIn() = userRepository.isKeepMeLoggedIn()


    fun onLogout() {
        userRepository.logOut()
        //  userRepository.saveUserProfile(null)
    }

    suspend fun getUserFromDisk() = userRepository.getUserFromDisk()
    fun saveToDisk(userId: String, password: String, companyId: Int) =
        userRepository.saveToDisk(userId, password, companyId)

    fun updateConfig(remoteConfigData: RemoteConfigData) =
        userRepository.updateConfig(remoteConfigData)

    fun getremoteConfig() = userRepository.getremoteConfig()

}

