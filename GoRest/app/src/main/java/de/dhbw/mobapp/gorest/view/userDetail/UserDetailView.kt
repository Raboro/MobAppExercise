package de.dhbw.mobapp.gorest.view.userDetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.dhbw.mobapp.gorest.R
import de.dhbw.mobapp.gorest.dto.UserDto
import de.dhbw.mobapp.gorest.dto.UserViewDto
import de.dhbw.mobapp.gorest.ui.theme.GoRestTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailView(userDto: UserDto, userDetailViewModel: UserDetailViewModel) {
    val userViewDto by remember {
        mutableStateOf(UserViewDto.from(userDto))
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(R.string.edit_user)) })
        },
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            if (userDetailViewModel.loading) {
                CircularProgressIndicator(modifier = Modifier.align(CenterHorizontally))
            } else {
                UserDetailViewContent(userViewDto, userDetailViewModel)
            }
        }
    }
}

@Composable
private fun UserDetailViewContent(
    userViewDto: UserViewDto,
    userDetailViewModel: UserDetailViewModel
) {
    var error: String by remember {
        mutableStateOf("")
    }

    Text(text = "ID: ${userViewDto.id}")
    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = userViewDto.name,
        onValueChange = { userViewDto.name = it })
    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = userViewDto.email,
        onValueChange = { userViewDto.email = it })
    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = userViewDto.gender,
        onValueChange = { userViewDto.gender = it })

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = CenterHorizontally
    ) {
        if (userViewDto.name.isNotEmpty()
            && userViewDto.gender.isNotEmpty()
            && userViewDto.email.isNotEmpty()
            && userViewDto.status.isNotEmpty()
        ) {
            Button(
                onClick = {
                    userDetailViewModel.updateUser(userViewDto.toUserDto()) { errorMessage ->
                        error = errorMessage
                    }
                },
                modifier = Modifier.padding(bottom = 10.dp)
            ) {
                Text(text = stringResource(R.string.save))
            }
        }
        Button(onClick = {
            userDetailViewModel.deleteUser(userViewDto.id) { errorMessage ->
                error = errorMessage
            }
        }) {
            Text(text = stringResource(R.string.delete))
        }
        if (error.isNotEmpty()) {
            Text(text = "Error: $error")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserDetailPreview() {
    GoRestTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            UserDetailView(
                userDto = UserDto(1, "Kek", "kek@kek.com", "male", "active"),
                userDetailViewModel = UserDetailViewModel()
            )
        }
    }
}

