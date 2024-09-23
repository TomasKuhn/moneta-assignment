package cz.kuhnt.moneta.assignment.system

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cz.kuhnt.moneta.assignment.feature.players.system.PlayersScreen
import cz.kuhnt.moneta.assignment.presentation.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun MainScreen() {
    val viewModel = koinViewModel<MainViewModel>()

    Content()
}

@Composable
private fun Content() {
    val navController = rememberNavController()
    MainNavHost(navController)
}

@Composable
fun MainNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Route.Initial(),
    ) {
        composable(Route.Players()) { PlayersScreen() }
    }
}
