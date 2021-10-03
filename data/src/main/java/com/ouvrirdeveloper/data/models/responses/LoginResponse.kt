package com.ouvrirdeveloper.data.models.responses

import com.ouvrirdeveloper.core.model.Mapper
import com.ouvrirdeveloper.domain.models.User
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponse(
    @Json(name = "UserInfo")
    val userInfo: List<UserInfo>
) : Mapper<User> {
    override fun toDomainModel(): User? {
        return userInfo.firstOrNull()?.run {
            User(
                userId = "",
                password = "",
                usr_ID_N = usrIDN,
                usr_LoginID_V = usrLoginIDV,
                usr_Name_V = usrNameV,
                rol_ID_N = rolIDN
            )
        }
    }
}

@JsonClass(generateAdapter = true)
data class UserInfo(
    @Json(name = "Usr_ID_N")
    val usrIDN: Long,

    @Json(name = "Usr_LoginID_V")
    val usrLoginIDV: String,

    @Json(name = "Usr_Name_V")
    val usrNameV: String,

    @Json(name = "Rol_ID_N")
    val rolIDN: Long
)
