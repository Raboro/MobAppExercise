package de.dhbw.mobapp.gorest.view.userDetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import de.dhbw.mobapp.gorest.dto.UserDto
import de.dhbw.mobapp.gorest.service.UserService
import kotlinx.coroutines.launch

class UserDetailViewModel : ViewModel() {
    lateinit var navController: NavController
    private val userService = UserService()

    var errorMessage: String by mutableStateOf("")
    var loading: Boolean by mutableStateOf(false)

    fun updateUser(userDto: UserDto, callback: (String) -> Unit) {
        viewModelScope.launch {
            errorMessage = ""
            loading = true

            try {
                userService.updateUser(userDto)
                navController.navigateUp()
            } catch (e: Exception) {
                errorMessage = e.message.toString()
                callback(errorMessage)
            } finally {
                loading = false
            }
        }
    }

    fun deleteUser(userId: Int, callback: (String) -> Unit) {
        viewModelScope.launch {
            errorMessage = ""
            loading = true

            try {
                userService.deleteUser(userId)
                navController.navigateUp()
            } catch (e: Exception) {
                errorMessage = e.message.toString()
                callback(errorMessage)
            } finally {
                loading = false
            }
        }
    }

}