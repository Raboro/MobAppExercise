package de.dhbw.mobapp.gorest.view.userDetail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import de.dhbw.mobapp.gorest.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailView(userDetailViewModel: UserDetailViewModel) {
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
                UserDetailViewContent(
                    userDetailViewModel,
                    userDetailViewModel::deleteUser,
                    userDetailViewModel::addUser,
                    userDetailViewModel::updateUser,
                )
            }
        }
    }
}

@Composable
private fun UserDetailViewContent(
    userDetailViewModel: UserDetailViewModel,
    deleteUser: () -> Unit,
    addUser: () -> Unit,
    updateUser: () -> Unit,
) {
    if (userDetailViewModel.hasId()) {
        Text(text = "ID: ${userDetailViewModel.userViewDto.id}")
    }
    TextField(
        placeholder = { Text(text = "Name eingeben") },
        modifier = Modifier
            .fillMaxWidth(),
        value = userDetailViewModel.userViewDto.name,
        onValueChange = { userDetailViewModel.userViewDto.name = it })
    TextField(
        placeholder = { Text(text = "Email eingeben") },
        modifier = Modifier
            .fillMaxWidth(),
        value = userDetailViewModel.userViewDto.email,
        onValueChange = { userDetailViewModel.userViewDto.email = it })
    TextField(
        placeholder = { Text(text = "Gender eingeben") },
        modifier = Modifier
            .fillMaxWidth(),
        value = userDetailViewModel.userViewDto.gender,
        onValueChange = { userDetailViewModel.userViewDto.gender = it })

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = CenterHorizontally
    ) {
        AnimatedVisibility(visible = userDetailViewModel.userCanBeInitialized()) {
            Button(
                onClick = {
                    if (!userDetailViewModel.hasId()) {
                        addUser()
                    } else {
                        updateUser()
                    }
                },
                modifier = Modifier.padding(bottom = 10.dp)
            ) {
                Text(text = stringResource(R.string.save))
            }
        }
        AnimatedVisibility(visible = userDetailViewModel.hasId()) {
            Button(onClick = deleteUser) {
                Text(text = stringResource(R.string.delete))
            }
        }
        AnimatedVisibility(visible = userDetailViewModel.errorMessage.isNotEmpty()) {
            Text(text = userDetailViewModel.errorMessage, color = Color.Red)
        }
    }
}
