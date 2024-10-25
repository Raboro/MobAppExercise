package de.dhbw.mobapp.gorest.view.userDetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.dhbw.mobapp.gorest.R
import de.dhbw.mobapp.gorest.dto.UserDto
import de.dhbw.mobapp.gorest.ui.theme.GoRestTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailView(userDto: UserDto) {
    var name by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var gender by remember {
        mutableStateOf("")
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
            Text(text = "ID: ${userDto.id}")
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = name,
                onValueChange = { name = it })
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = email,
                onValueChange = { email = it })
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = gender,
                onValueChange = { gender = it })

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = {}, modifier = Modifier.padding(bottom = 10.dp)) {
                    Text(text = stringResource(R.string.save))
                }
                Button(onClick = {}) {
                    Text(text = stringResource(R.string.delete))
                }
            }
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
            UserDetailView(userDto = UserDto(1, "Kek", "kek@kek.com", "male", "active"))
        }
    }
}

