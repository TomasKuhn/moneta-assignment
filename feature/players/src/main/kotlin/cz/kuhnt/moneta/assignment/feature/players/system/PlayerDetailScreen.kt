package cz.kuhnt.moneta.assignment.feature.players.system

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cz.kuhnt.moneta.assignment.feature.players.presentation.PlayerDetailViewModel
import cz.kuhnt.moneta.assignment.library.design.system.Dimensions
import cz.kuhnt.moneta.assignment.library.design.system.MonetaTheme
import cz.kuhnt.moneta.assignment.library.design.system.MonetaTopAppBar
import cz.kuhnt.moneta.assignment.localization.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlayerDetailScreen() {
    val viewModel = koinViewModel<PlayerDetailViewModel>()
    val state by viewModel.states.collectAsState()

    Content(
        state = state,
        onBack = viewModel::onBack
    )
}

@Composable
private fun Content(
    state: PlayerDetailViewModel.State,
    onBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            MonetaTopAppBar(
                title = state.name,
                onBackClick = onBack,
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            KeyValueRow(stringResource(R.string.players_height), state.height)
            HorizontalDivider(modifier = Modifier.padding(horizontal = Dimensions.paddingM))
            KeyValueRow(stringResource(R.string.players_weight), state.weight)
            HorizontalDivider(modifier = Modifier.padding(horizontal = Dimensions.paddingM))
            KeyValueRow(stringResource(R.string.players_college), state.college)
            HorizontalDivider(modifier = Modifier.padding(horizontal = Dimensions.paddingM))
            KeyValueRow(stringResource(R.string.players_country), state.country)
            HorizontalDivider(modifier = Modifier.padding(horizontal = Dimensions.paddingM))
            KeyValueRow(stringResource(R.string.players_position), state.position)
            HorizontalDivider(modifier = Modifier.padding(horizontal = Dimensions.paddingM))
            KeyValueRow(stringResource(R.string.players_team), state.team)
        }
    }
}

@Composable
private fun KeyValueRow(key: String, value: String) {
    Row(
        modifier = Modifier.padding(
            horizontal = Dimensions.paddingM,
            vertical = Dimensions.paddingS
        )
    ) {
        Text(text = key, modifier = Modifier.weight(1f))
        Text(text = value)
    }
}

@Preview
@Composable
private fun PlayerDetailPreview() = MonetaTheme {
    Content(
        state = PlayerDetailViewModel.State(
            name = "Stephen Curry",
            height = "6-2",
            weight = "185",
            college = "Davidson College",
            country = "USA",
            position = "G1",
            team = "Warriors"
        ),
        onBack = {}
    )
}
