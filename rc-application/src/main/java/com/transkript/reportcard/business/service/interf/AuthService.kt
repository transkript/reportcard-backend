package com.transkript.reportcard.business.service.interf

import com.transkript.reportcard.api.dto.request.UserRequest
import com.transkript.reportcard.api.dto.response.UserResponse

interface AuthService {
    fun registerUser(userRequest: UserRequest.Register): UserResponse.Register
    fun loginUser(userRequest: UserRequest.Login): UserResponse.Login
}
