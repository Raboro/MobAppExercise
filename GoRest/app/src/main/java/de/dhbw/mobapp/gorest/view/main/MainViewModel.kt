package de.dhbw.mobapp.gorest.view.main

import android.graphics.BitmapFactory
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.dhbw.mobapp.gorest.dto.UserDto
import de.dhbw.mobapp.gorest.service.UserService
import kotlinx.coroutines.launch

class MainViewModel(private val navToDetail: (id: Int) -> Unit) : ViewModel() {
    private val userService = UserService()

    var users: MutableList<UserDto> by mutableStateOf(mutableListOf())
    var errorMessage: String by mutableStateOf("")
    var loading: Boolean by mutableStateOf(false)

    lateinit var userImage: ImageBitmap

    fun getAllUsers() {
        viewModelScope.launch {
            errorMessage = ""
            loading = true

            try {
                val allUsers = userService.getAllUsers()
                val loadUserImage = userService.getUserImage()
                val bitmap = BitmapFactory.decodeByteArray(loadUserImage, 0, loadUserImage.size)
                userImage = bitmap.asImageBitmap()
                users = allUsers.toMutableList()
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            } finally {
                loading = false
            }
        }
    }

    fun navigateToDetail(id: Int) {
        this.navToDetail(id)
    }
}
