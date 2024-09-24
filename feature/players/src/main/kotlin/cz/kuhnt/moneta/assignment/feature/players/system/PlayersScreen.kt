package cz.kuhnt.moneta.assignment.feature.players.system

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cz.kuhnt.moneta.assignment.feature.players.presentation.PlayersViewModel
import cz.kuhnt.moneta.assignment.library.design.system.ErrorDialog
import cz.kuhnt.moneta.assignment.library.design.system.MonetaTheme
import cz.kuhnt.moneta.assignment.localization.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlayersScreen() {
    val viewModel = koinViewModel<PlayersViewModel>()
    val state by viewModel.states.collectAsState()

    Content(
        state = state,
        onErrorDismiss = viewModel::onErrorDismiss
    )
}

@Composable
private fun Content(
    state: PlayersViewModel.State,
    onErrorDismiss: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.players_title)) }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        if (state.isLoading) {
            Loading()
        } else if (state.error != null) {
            Error(
                errorMessage = state.error,
                onDismiss = onErrorDismiss
            )
        } else {
            Players(
                state = state,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
private fun Loading() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun Error(
    errorMessage: String,
    onDismiss: () -> Unit
) {
    ErrorDialog(
        title = stringResource(id = R.string.common_error),
        text = errorMessage,
        onDismiss = onDismiss
    )
}

@Composable
private fun Players(
    modifier: Modifier,
    state: PlayersViewModel.State
) {
    LazyColumn(modifier = modifier) {
        items(state.playerNames) { name ->
            Text(text = name)
        }
    }
}

@Preview
@Composable
private fun PlayersScreenPreview() = MonetaTheme {
    Content(
        state = PlayersViewModel.State(
            playerNames = listOf("Player 1", "Player 2", "Player 3")
        ),
        onErrorDismiss = {}
    )
}

@Preview
@Composable
private fun PlayersScreenLoadingPreview() = MonetaTheme {
    Content(
        state = PlayersViewModel.State(
            isLoading = true
        ),
        onErrorDismiss = {}
    )
}

@Preview
@Composable
private fun PlayersScreenErrorPreview() = MonetaTheme {
    Content(
        state = PlayersViewModel.State(
            error = "Something went wrong"
        ),
        onErrorDismiss = {}
    )
}
