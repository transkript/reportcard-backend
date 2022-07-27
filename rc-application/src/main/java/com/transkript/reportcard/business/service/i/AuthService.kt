package com.transkript.reportcard.business.service.i

import com.transkript.reportcard.api.dto.request.UserRequest
import com.transkript.reportcard.api.dto.response.UserResponse
import javax.servlet.http.HttpSession

interface AuthService {
    fun registerUser(userRequest: UserRequest.Register): UserResponse.Register
    fun loginUser(userRequest: UserRequest.Login, session: HttpSession): UserResponse.Login
    fun logout(session: HttpSession): String
}
