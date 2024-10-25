package de.dhbw.mobapp.gorest.view.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.dhbw.mobapp.gorest.R
import de.dhbw.mobapp.gorest.view.main.cell.UserCell

@Composable
fun MainView(navController: NavController, mainViewModel: MainViewModel) {
    LaunchedEffect(Unit) { // mit Unit, einmalig ausgeführt beim rendern -> init laden, da sich unit nie ändert
        mainViewModel.getAllUsers()
    }

    Column(modifier = Modifier.padding(5.dp)) {
        Button(onClick = { navController.navigate("userDetailView") }) {
            Text(text = stringResource(R.string.new_user))
        }

        Column(modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()) {
            if (mainViewModel.loading) {
                CircularProgressIndicator(modifier = Modifier.align(CenterHorizontally))
            } else {
                MainList(navController = navController, mainViewModel = mainViewModel)
            }
        }
    }
}

@Composable
fun MainList(navController: NavController, mainViewModel: MainViewModel) {
    if (mainViewModel.errorMessage.isEmpty()) {
        LazyColumn {
            itemsIndexed(items = mainViewModel.users) { index, item ->
                UserCell(userDto = item)
            }
        }
    } else {
        // Color ganz schlecht, nur hier als Test
        Text(text = mainViewModel.errorMessage, color = Color.Red, fontWeight = FontWeight.Bold)
    }
}