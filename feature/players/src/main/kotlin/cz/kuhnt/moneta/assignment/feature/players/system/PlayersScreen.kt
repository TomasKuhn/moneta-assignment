package cz.kuhnt.moneta.assignment.feature.players.system

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
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
import cz.kuhnt.moneta.assignment.library.design.system.MonetaTopAppBar
import cz.kuhnt.moneta.assignment.library.design.system.Typography
import cz.kuhnt.moneta.assignment.localization.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlayersScreen() {
    val viewModel = koinViewModel<PlayersViewModel>()
    val state by viewModel.states.collectAsState()

    Content(
        state = state,
        onPlayerDetail = viewModel::onPlayerDetail,
        onLoadNextPage = viewModel::onFetchPlayers,
        onErrorDismiss = viewModel::onErrorDismiss
    )
}

@Composable
private fun Content(
    state: PlayersViewModel.State,
    onLoadNextPage: () -> Unit,
    onPlayerDetail: (Player) -> Unit,
    onErrorDismiss: () -> Unit,
) {
    Scaffold(
        topBar = {
            MonetaTopAppBar(title = stringResource(R.string.player_title))
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        if (state.error != null) {
            Error(
                errorMessage = state.error,
                onDismiss = onErrorDismiss
            )
        } else {
            Players(
                modifier = Modifier.padding(innerPadding),
                state = state,
                onLoadNextPage = onLoadNextPage,
                onPlayerDetail = onPlayerDetail
            )
        }
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
    state: PlayersViewModel.State,
    onLoadNextPage: () -> Unit,
    onPlayerDetail: (Player) -> Unit,
) {
    val listState = rememberLazyListState()

    PagingEffect(
        listState = listState,
        onLoadNextPage = onLoadNextPage
    )

    LazyColumn(
        state = listState,
        modifier = modifier,
    ) {
        items(state.players) { player ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onPlayerDetail(player) }
                    .padding(
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
        item {
            if (state.isLoading) {
                Loading()
            }
        }
    }
}

@Composable
private fun PagingEffect(listState: LazyListState, onLoadNextPage: () -> Unit) {
    LaunchedEffect(key1 = listState) {
        snapshotFlow { listState.layoutInfo }
            .collect { layoutInfo ->
                if (layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1) {
                    onLoadNextPage()
                }
            }
    }
}

@Composable
private fun Loading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimensions.paddingM)
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview
@Composable
private fun PlayersScreenPreview() = MonetaTheme {
    Content(
        state = PlayersViewModel.State(
            players = listOf(
                Player(
                    id = 1,
                    name = "Stephen Curry",
                    position = "G1",
                    height = "185",
                    weight = "6-2",
                    college = "Davidson College",
                    country = "USA",
                    team = Team(
                        id = 1,
                        name = "Warriors",
                        conference = "East",
                        division = "Pacific",
                        city = "Oakland",
                        fullName = "Oakland Warriors",
                        abbreviation = "OKC"
                    )
                )
            )
        ),
        onLoadNextPage = {},
        onPlayerDetail = {},
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
        onLoadNextPage = {},
        onPlayerDetail = {},
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
        onLoadNextPage = {},
        onPlayerDetail = {},
        onErrorDismiss = {}
    )
}
