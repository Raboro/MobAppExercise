package de.dhbw.mobapp.gorest.view.userDetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import de.dhbw.mobapp.gorest.dto.UserViewDto
import de.dhbw.mobapp.gorest.service.UserService
import kotlinx.coroutines.launch

class UserDetailViewModel : ViewModel() {
    lateinit var navController: NavController
    private val userService = UserService()

    var errorMessage: String by mutableStateOf("")
    var loading: Boolean by mutableStateOf(false)
    var userViewDto: UserViewDto by mutableStateOf(UserViewDto(-1, "", "", "", "active"))

    fun updateUser() {
        viewModelScope.launch {
            errorMessage = ""
            loading = true

            try {
                userService.updateUser(userViewDto.toUserDto())
                navController.navigateUp()
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            } finally {
                loading = false
            }
        }
    }

    fun addUser() {
        viewModelScope.launch {
            errorMessage = ""
            loading = true

            try {
                userService.addUser(userViewDto.toUserDto())
                navController.navigateUp()
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            } finally {
                loading = false
            }
        }
    }

    fun deleteUser() {
        viewModelScope.launch {
            errorMessage = ""
            loading = true

            try {
                userService.deleteUser(userViewDto.id)
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

    fun toErrorMessage(): String {
        return errorMessage
    }

}