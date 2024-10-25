package de.dhbw.mobapp.gorest.service

import de.dhbw.mobapp.gorest.api.ApiManager
import de.dhbw.mobapp.gorest.dto.UserDto
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse

class UserService(private val apiManager: ApiManager = ApiManager()) {

    suspend fun getAllUsers(): List<UserDto> {
        return apiManager.jsonHttpClient.get("users").body()
    }

    suspend fun addUser(userDto: UserDto): UserDto {
        return apiManager.jsonHttpClient.post("users") {
            setBody(userDto)
        }.body()
    }

    suspend fun getUser(usedId: Int): UserDto {
        return apiManager.jsonHttpClient.get("users/$usedId").body();
    }

    suspend fun updateUser(userDto: UserDto): UserDto {
        return apiManager.jsonHttpClient.put("users/${userDto.id}") {
            setBody(userDto)
        }.body()
    }

    suspend fun deleteUser(usedId: Int): HttpResponse {
        return apiManager.jsonHttpClient.delete("users/$usedId")
    }
}