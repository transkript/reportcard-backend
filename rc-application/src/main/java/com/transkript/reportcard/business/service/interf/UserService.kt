package com.transkript.reportcard.business.service.interf

import com.transkript.reportcard.api.dto.UserDto
import com.transkript.reportcard.api.dto.request.UserRequest
import com.transkript.reportcard.api.dto.response.EntityResponse
import com.transkript.reportcard.api.dto.response.UserResponse
import com.transkript.reportcard.data.entity.User

interface UserService {
    
    fun save(userDto: UserDto): EntityResponse
    fun retrieveEntityByUsername(username: String): User
}
