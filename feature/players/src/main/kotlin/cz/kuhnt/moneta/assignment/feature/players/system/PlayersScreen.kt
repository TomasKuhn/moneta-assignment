package cz.kuhnt.moneta.assignment.feature.players.system

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
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
import cz.kuhnt.moneta.assignment.feature.players.model.Player
import cz.kuhnt.moneta.assignment.feature.players.model.Team
import cz.kuhnt.moneta.assignment.feature.players.presentation.PlayersViewModel
import cz.kuhnt.moneta.assignment.library.design.system.Dimensions
import cz.kuhnt.moneta.assignment.library.design.system.ErrorDialog
import cz.kuhnt.moneta.assignment.library.design.system.MonetaTheme
import cz.kuhnt.moneta.assignment.library.design.system.Typography
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
        items(state.players) { player ->
            Column(
                modifier = Modifier.padding(
                    horizontal = Dimensions.paddingM,
                    vertical = Dimensions.paddingS,
                )
            ) {
                Text(
                    text = player.name,
                    style = Typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(text = player.position)
                Text(text = player.team.name)
            }
            HorizontalDivider(modifier = Modifier.padding(horizontal = Dimensions.paddingM))
        }
    }
}

@Preview
@Composable
private fun PlayersScreenPreview() = MonetaTheme {
    Content(
        state = PlayersViewModel.State(
            players = listOf(
                Player("Stephen Curry", "G1", Team("Warriors"))
            )
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
