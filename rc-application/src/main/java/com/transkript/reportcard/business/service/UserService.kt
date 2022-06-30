package com.transkript.reportcard.business.service

import com.transkript.reportcard.api.dto.UserDto
import com.transkript.reportcard.api.dto.request.UserRequest
import com.transkript.reportcard.api.dto.response.EntityResponse
import com.transkript.reportcard.api.dto.response.UserResponse
import com.transkript.reportcard.data.entity.User

interface UserService {
    fun registerUser(userRequest: UserRequest.Register): UserResponse.Register
    fun loginUser(userRequest: UserRequest.Login): UserResponse.Login
    
    fun save(userDto: UserDto): EntityResponse
    fun retrieveEntityByUsername(username: String): User
}
