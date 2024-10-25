package de.dhbw.mobapp.gorest.service

import de.dhbw.mobapp.gorest.api.ApiManager
import de.dhbw.mobapp.gorest.dto.UserDto
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class UserService(private val apiManager: ApiManager = ApiManager()) {

    suspend fun getAllUsers(): List<UserDto> {
        return apiManager.jsonHttpClient.get("users").body()
    }

    suspend fun addUser(userDto: UserDto): UserDto {
        return apiManager.jsonHttpClient.post("users") {
            setBody(userDto)
        }.body()
    }
}