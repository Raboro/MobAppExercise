package de.dhbw.mobapp.l2cpcontrols

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.dhbw.mobapp.l2cpcontrols.ui.theme.L2CPControlsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            L2CPControlsTheme {
                L2CPControlsApp()
            }
        }
    }
}

@Composable
fun L2CPControlsApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "mainView") {
        composable(route = "mainView") {
            MainView(navController = navController)
        }
    }
}