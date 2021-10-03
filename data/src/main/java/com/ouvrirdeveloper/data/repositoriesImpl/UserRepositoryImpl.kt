package com.ouvrirdeveloper.data.repositoriesImpl

import com.ouvrirdeveloper.domain.repositories.UserRepository
import com.ouvrirdeveloper.data.datasource.UserDataSource
import com.ouvrirdeveloper.data.helper.KeystoreHelper
import com.ouvrirdeveloper.data.helper.PreferenceHelper
import com.ouvrirdeveloper.data.models.entities.PendingTaskEntity
import com.ouvrirdeveloper.data.safeApiCall
import com.ouvrirdeveloper.domain.models.PendingTask
import com.ouvrirdeveloper.domain.models.Resource
import com.ouvrirdeveloper.domain.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserRepositoryImpl(
    private val userDataSource: UserDataSource,
    private val preferenceHelper: PreferenceHelper,
) : UserRepository {
    /*override fun getUserList(): Flow<Resource<List<User>>> {
        return flow {
            val response = safeApiCall { service.getUserList() }
            when (response.status) {
                Status.SUCCESS -> emit(Resource.success(response.data?.toDomainModel()))
                Status.GENERIC_ERROR -> emit(
                    Resource.genericError(
                        response.message ?: "",
                        response.data?.toDomainModel()
                    )
                )
                Status.NETWORK_ERROR -> emit(
                    Resource.networkError(
                        response.message ?: "",
                        response.data?.toDomainModel()
                    )
                )
                Status.HTTP_ERROR -> emit(
                    Resource.httpError(
                        response.errorCode,
                        response.message ?: "",
                        response.data?.toDomainModel()
                    )
                )
                Status.LOADING -> emit(Resource.loading(response.data?.toDomainModel()))
                Status.INITIAL -> emit(Resource.initial(response.data?.toDomainModel()))
                Status.EMPTY -> emit(Resource.empty(response.data?.toDomainModel()))
            }
        }
    }*/

    private val keyStoreHelper: KeystoreHelper by lazy {
        KeystoreHelper()
    }

    override fun login(loginDetails: User): Flow<Resource<User?>> {
        return flow<Resource<User?>> {
            val kk = safeApiCall {
                userDataSource.login(loginDetails).data.toDomainModel()?.also {
                    it.password = loginDetails.password
                    it.userId = loginDetails.userId
                }
            }
            emit(kk)
        }.flowOn(Dispatchers.IO)
    }

    override fun viewPendingTaskList(loadType: Int): Flow<Resource<List<PendingTask>>> {
        return flow {
            val response = safeApiCall {
                userDataSource.viewPendingTaskList(loadType).data.toDomainModel()
            }
            emit(response)
        }.flowOn(Dispatchers.IO)
    }


    override fun logOut() {
        /*clearTokenDetails()
        setIsUserLoggedIn(false)
        val loginDetails = getKeepMeSignedInDetail()
        loginDetails.password = ""
        saveIsKeepMeLoggedIn(loginDetails, isKeepMeLoggedIn())*/
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


    private fun getEncryptedData(dataToEncrypt: String?) = dataToEncrypt?.let {
        keyStoreHelper.encryptData(it)
    }


}