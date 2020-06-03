package com.wwm.trackappsyncapp.auth

import androidx.lifecycle.LiveData

interface AuthenticationService {
    val loggedIn: LiveData<Boolean>
    var accessToken: String?
    var refreshToken: String?
    var idToken: String?

    suspend fun login(username: String, password: String)

    suspend fun logout()

    suspend fun refresh()

    fun isTokenExpired(timeSpan: Int?, networkErrorCode: Int?): Boolean

    fun updateOAuthData(data: LoginResponse?)

    fun clearOAuthData()

    fun isExpiredStatusCode(code: Int): Boolean


}