package de.dhbw.mobapp.gorest.dto

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class UserViewDto(
    val id: Int,
    name: String,
    email: String,
    gender: String,
    status: String
) {
    var name: String by mutableStateOf(name)
    var email: String by mutableStateOf(email)
    var gender: String by mutableStateOf(gender)
    var status: String by mutableStateOf(status)

    companion object {
        fun from(userDto: UserDto): UserViewDto {
            return UserViewDto(
                userDto.id,
                userDto.name,
                userDto.email,
                userDto.gender,
                userDto.status
            )
        }
    }

    fun toUserDto(): UserDto {
        return UserDto(id, name, email, gender, status)
    }
}