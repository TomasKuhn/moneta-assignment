package cz.kuhnt.moneta.assignment.system

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cz.kuhnt.moneta.assignment.feature.players.system.PlayerDetailScreen
import cz.kuhnt.moneta.assignment.feature.players.system.PlayersScreen
import cz.kuhnt.moneta.assignment.feature.players.system.TeamDetailScreen
import cz.kuhnt.moneta.assignment.model.NavigationEvent
import cz.kuhnt.moneta.assignment.presentation.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun MainScreen() {
    val viewModel = koinViewModel<MainViewModel>()
    val state by viewModel.states.collectAsState()
    
    Content(
        state = state,
        onNavigationEventConsumed = viewModel::onNavigationEventConsumed
    )
}

@Composable
private fun Content(
    state: MainViewModel.State,
    onNavigationEventConsumed: () -> Unit
) {
    val navController = rememberNavController()
    NavigationEffect(
        navController = navController,
        state = state,
        onNavigationEventConsumed = onNavigationEventConsumed
    )
    MainNavHost(navController = navController)
}

@Composable
fun MainNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Route.Initial(),
    ) {
        composable(Route.Players()) { PlayersScreen() }
        composable(Route.PlayerDetail()) { PlayerDetailScreen() }
        composable(Route.TeamDetail()) { TeamDetailScreen() }
    }
}

@Composable
private fun NavigationEffect(
    navController: NavHostController,
    state: MainViewModel.State,
    onNavigationEventConsumed: () -> Unit
) {
    state.navigationEvent?.let { navigationEvent ->
        SideEffect {
            when (navigationEvent) {
                NavigationEvent.BackEvent -> {
                    navController.navigateUp()
                    onNavigationEventConsumed()
                }
                is NavigationEvent.ForwardEvent -> {
                    navController.navigate(navigationEvent.route())
                    onNavigationEventConsumed()
                }
            }
        }
    }
}
