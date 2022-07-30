package com.transkript.reportcard.business.service.i

import com.transkript.reportcard.api.dto.request.UserRequest
import com.transkript.reportcard.api.dto.request.UserRequest.Login
import com.transkript.reportcard.api.dto.request.UserRequest.Logout
import com.transkript.reportcard.api.dto.response.UserResponse
import com.transkript.reportcard.api.dto.response.UserResponse.Auth
import javax.servlet.http.HttpSession

interface AuthService {
    fun registerUser(userRequest: UserRequest.Register): UserResponse.Register
    fun loginUser(userRequest: Login, session: HttpSession): Auth
    fun logout(userRequest: Logout, session: HttpSession): Auth
}
