package de.dhbw.mobapp.gorest.view.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.dhbw.mobapp.gorest.dto.UserDto
import de.dhbw.mobapp.gorest.service.UserService
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val userService = UserService()

    var users: List<UserDto> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")
    var loading: Boolean by mutableStateOf(false)

    fun getAllUsers() {
        viewModelScope.launch {
            errorMessage = ""
            loading = true

            try {
                val allUsers = userService.getAllUsers()
                users = allUsers
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            } finally {
                loading = false
            }
        }
    }
}
