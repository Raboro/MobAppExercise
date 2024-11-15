package de.dhbw.mobapp.gorest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import de.dhbw.mobapp.gorest.dto.UserDto
import de.dhbw.mobapp.gorest.dto.UserViewDto
import de.dhbw.mobapp.gorest.ui.theme.GoRestTheme
import de.dhbw.mobapp.gorest.view.main.MainView
import de.dhbw.mobapp.gorest.view.main.MainViewModel
import de.dhbw.mobapp.gorest.view.userDetail.UserDetailView
import de.dhbw.mobapp.gorest.view.userDetail.UserDetailViewModel

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
    val navigateToDetail: (Int) -> Unit = { navController.navigate("userDetailView/$it") }
    val mainViewModel = remember {
        MainViewModel(navToDetail = navigateToDetail)
    }
    val userDetailViewModel = viewModel<UserDetailViewModel>()
    userDetailViewModel.navController = navController

    NavHost(navController = navController, startDestination = "mainView", modifier = modifier) {
        composable("mainView") {
            MainView(mainViewModel)
        }
        composable(
            "userDetailView/{index}",
            arguments = listOf(navArgument("index") { type = NavType.IntType })
        ) { backStackEntry ->
            var index = backStackEntry.arguments?.getInt("index") ?: return@composable
            if (index == -1) {
                mainViewModel.users.add(UserDto(-1, "", "", "", "active"))
                index = mainViewModel.users.lastIndex
            }
            val user = mainViewModel.users[index]
            userDetailViewModel.userViewDto = UserViewDto.from(user)
            UserDetailView(userDetailViewModel)
        }
    }
}
