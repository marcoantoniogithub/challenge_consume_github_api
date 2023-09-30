package com.example.challenge_consume_github_api.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.challenge_consume_github_api.ui.view.repositories.RepositoriesScreen
import com.example.challenge_consume_github_api.ui.view.main.MainScreen
import com.example.challenge_consume_github_api.ui.view.userdetails.UserDetailsScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Screen.Main.route) {
        composable(Screen.Main.route) {
            MainScreen(navController)
        }
        composable(Screen.UserDetailsScreen.route.plus(Screen.UserDetailsScreen.argsPath)) {
            it.arguments?.getString(Screen.UserDetailsScreen.argsName)?.let {
                UserDetailsScreen(navController, it)
            }
        }
        composable(Screen.RepositoriesScreen.route.plus(Screen.RepositoriesScreen.argsPath)) {
            it.arguments?.getString(Screen.RepositoriesScreen.argsName)?.let {
                RepositoriesScreen(navController, it)
            }
        }
    }
}

sealed class Screen(val route: String, val argsName: String = "", val argsPath: String= "") {
    object Main : Screen("main")
    object UserDetailsScreen : Screen("user-details" , argsName = "login-user", argsPath = "/{login-user}")
    object RepositoriesScreen : Screen("repositories" , argsName = "login-user", argsPath = "/{login-user}")
}
