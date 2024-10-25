package de.dhbw.mobapp.gorest.view.main.cell

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import de.dhbw.mobapp.gorest.dto.UserDto

@Composable
fun UserCell(userDto: UserDto) {
    Column {
        Text("${userDto.id}")
        Text(userDto.name)
    }
}