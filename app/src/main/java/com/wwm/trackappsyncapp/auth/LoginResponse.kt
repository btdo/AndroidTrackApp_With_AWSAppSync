package com.wwm.trackappsyncapp.auth

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

// Be careful what Json converter library is used and what library field annotations are used.
// Here we use field annotations for Gson and Moshi libraries.
@JsonIgnoreProperties(ignoreUnknown = true)
data class LoginResponse(

    val access_token: String?,

    val refresh_token: String?,

    val AUTHENTICATION_LEVEL: String?,

    val scope: String?,

    val token_type: String?,

    val expires_in: Int?,

    val id_token: String?


) {
    var timeStamp: Long = 0
}