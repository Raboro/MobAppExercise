package de.dhbw.mobapp.gorest.view.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.dhbw.mobapp.gorest.R

@Composable
fun MainView(navController: NavController) {
    Column(modifier = Modifier.padding(5.dp)) {
        Button(onClick = { navController.navigate("userDetailView") }) {
            Text(text = stringResource(R.string.new_user))
        }
    }
}