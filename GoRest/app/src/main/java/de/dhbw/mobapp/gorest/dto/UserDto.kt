package de.dhbw.mobapp.gorest.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    //@SerialName("heißt in der api anders")
    val id: Int,
    var name: String,
    var email: String,
    var gender: String,
    var status: String
)