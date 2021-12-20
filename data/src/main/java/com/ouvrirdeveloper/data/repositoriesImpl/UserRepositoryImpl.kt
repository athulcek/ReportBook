package com.ouvrirdeveloper.data.repositoriesImpl

import android.content.SharedPreferences
import com.ouvrirdeveloper.core.BuildConfig
import com.ouvrirdeveloper.data.datasource.UserDataSource
import com.ouvrirdeveloper.data.helper.KeystoreHelper
import com.ouvrirdeveloper.data.helper.PreferenceHelper
import com.ouvrirdeveloper.data.helper.SharedConstants
import com.ouvrirdeveloper.data.models.entities.PendingTaskEntity
import com.ouvrirdeveloper.data.safeApiCall
import com.ouvrirdeveloper.domain.models.PendingTask
import com.ouvrirdeveloper.domain.models.RemoteConfigData
import com.ouvrirdeveloper.domain.models.Resource
import com.ouvrirdeveloper.domain.models.User
import com.ouvrirdeveloper.domain.repositories.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch


class UserRepositoryImpl(
    private val userDataSource: UserDataSource,
    private val preferenceHelper: PreferenceHelper

) : UserRepository {

    private val keyStoreHelper: KeystoreHelper by lazy {
        KeystoreHelper()
    }

    override fun login(loginDetails: User): Flow<Resource<User?>> {
        return flow<Resource<User?>> {
            val kk = safeApiCall {
                userDataSource.login(loginDetails).data?.toDomainModel()?.also {
                    it.password = loginDetails.password
                    it.userId = loginDetails.userId
                    it.companyId = loginDetails.companyId
                }
            }
            emit(kk)
        }.flowOn(Dispatchers.IO)
    }


    override fun logOut() {
        with(Dispatchers.IO) {
            val preferences: SharedPreferences = preferenceHelper.defaultPrefs()
            val editor = preferences.edit()
            editor.clear()
            editor.apply()
        }
    }

    override fun isKeepMeLoggedIn(): Boolean {
        return true
    }

    override suspend fun updateDbwithPendingTasks(data: List<PendingTask>) {
        userDataSource.updateDbwithPendingTasks(data.map {
            PendingTaskEntity(
                docsrchcode = it.docsrchcode,
                doctype = it.doctype,
                totalcount = it.totalcount
            )
        })
    }

    override suspend fun getUserFromDisk(): User? {
        if (preferenceHelper.getPref(
                SharedConstants.PrefUtil_IS_LOGGED_IN,
                false
            ) == true && preferenceHelper.getPref(
                SharedConstants.PrefUtil_KEEP_ME_LOGGED_IN,
                false
            ) == true
        ) {
            val user = User(
                userId = preferenceHelper.getPref(SharedConstants.PrefUtil_UNAME, "") ?: "",
                password = preferenceHelper.getPref(SharedConstants.PrefUtil_PASS_KEY, "") ?: "",
                companyId = preferenceHelper.getPref(SharedConstants.PrefUtil_COMPANY_ID, 0) ?: 0
            )
            return user
        }
        return null
    }

    override fun saveToDisk(userId: String, password: String, companyId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            preferenceHelper.setPref(SharedConstants.PrefUtil_KEEP_ME_LOGGED_IN, true)
            preferenceHelper.setPref(SharedConstants.PrefUtil_IS_LOGGED_IN, true)
            preferenceHelper.setPref(SharedConstants.PrefUtil_UNAME, userId)
            preferenceHelper.setPref(SharedConstants.PrefUtil_PASS_KEY, password)
            preferenceHelper.setPref(SharedConstants.PrefUtil_COMPANY_ID, companyId)
        }
    }

    override fun updateConfig(remoteConfigData: RemoteConfigData) {
        with(Dispatchers.IO) {
            preferenceHelper.setPref(
                SharedConstants.PrefUtil_CURRENT_VERSION_CODE,
                remoteConfigData.currentVersion
            )
            preferenceHelper.setPref(
                SharedConstants.PrefUtil_ENABLE_LOGIN,
                remoteConfigData.enable_login
            )
        }
    }

    override fun getremoteConfig(): Flow<RemoteConfigData> {
        return flow {
            val currentVersion =
                preferenceHelper.getPref(
                    SharedConstants.PrefUtil_CURRENT_VERSION_CODE,
                    BuildConfig.VERSION_CODE
                )
            val enable_login = preferenceHelper.getPref(SharedConstants.PrefUtil_ENABLE_LOGIN, true)
            emit(RemoteConfigData(enable_login ?: true, currentVersion ?: BuildConfig.VERSION_CODE))
        }
    }


    private fun getEncryptedData(dataToEncrypt: String?) = dataToEncrypt?.let {
        keyStoreHelper.encryptData(it)
    }


}