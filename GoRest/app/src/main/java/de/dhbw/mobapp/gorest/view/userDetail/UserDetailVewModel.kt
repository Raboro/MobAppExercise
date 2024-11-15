package de.dhbw.mobapp.gorest.view.userDetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import de.dhbw.mobapp.gorest.dto.UserDto
import de.dhbw.mobapp.gorest.dto.UserViewDto
import de.dhbw.mobapp.gorest.service.UserService
import kotlinx.coroutines.launch

class UserDetailViewModel : ViewModel() {
    lateinit var navController: NavController
    private val userService = UserService()

    var errorMessage: String by mutableStateOf("")
    var loading: Boolean by mutableStateOf(false)
    var userViewDto: UserViewDto by mutableStateOf(UserViewDto(-1, "", "", "", "active"))

    fun updateUser(userDto: UserDto) {
        viewModelScope.launch {
            errorMessage = ""
            loading = true

            try {
                userService.updateUser(userDto)
                navController.navigateUp()
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            } finally {
                loading = false
            }
        }
    }

    fun addUser(userDto: UserDto) {
        viewModelScope.launch {
            errorMessage = ""
            loading = true

            try {
                userService.addUser(userDto)
                navController.navigateUp()
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            } finally {
                loading = false
            }
        }
    }

    fun deleteUser(userId: Int) {
        viewModelScope.launch {
            errorMessage = ""
            loading = true

            try {
                userService.deleteUser(userId)
                navController.navigateUp()
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            } finally {
                loading = false
            }
        }
    }

    fun userCanBeInitialized(): Boolean {
        return userViewDto.name.isNotEmpty()
                && userViewDto.gender.isNotEmpty()
                && userViewDto.email.isNotEmpty()
                && userViewDto.status.isNotEmpty()
    }

    fun hasId(): Boolean {
        return userViewDto.id != -1
    }

}