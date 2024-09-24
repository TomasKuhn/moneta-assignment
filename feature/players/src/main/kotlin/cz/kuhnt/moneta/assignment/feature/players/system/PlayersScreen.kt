package cz.kuhnt.moneta.assignment.feature.players.system

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cz.kuhnt.moneta.assignment.feature.players.presentation.PlayersViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlayersScreen() {
    val viewModel = koinViewModel<PlayersViewModel>()
    val state by viewModel.states.collectAsState()

    Content(state)
}

@Composable
private fun Content(state: PlayersViewModel.State) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        LazyColumn(contentPadding = innerPadding) {
            items(state.playerNames) { name ->
                Text(text = name)
            }
        }
    }
}
