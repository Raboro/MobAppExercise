package de.dhbw.mobapp.gorest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.dhbw.mobapp.gorest.ui.theme.GoRestTheme
import de.dhbw.mobapp.gorest.view.main.MainView
import de.dhbw.mobapp.gorest.view.userDetail.UserDetailView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GoRestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GoRestApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun GoRestApp(modifier: Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "mainView", modifier = modifier) {
        composable("mainView") {
            MainView(navController = navController)
        }
        composable("userDetailView") {
            UserDetailView()
        }
    }
}