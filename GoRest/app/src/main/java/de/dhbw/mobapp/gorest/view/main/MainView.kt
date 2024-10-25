package de.dhbw.mobapp.gorest.view.main

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun MainView(navController: NavController) {
    Button(onClick = { navController.navigate("userDetailView") }) {
        Text(text = "Neuen Nutzer hinzufügen")
    }
}