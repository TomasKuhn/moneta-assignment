package cz.kuhnt.moneta.assignment.feature.players.system

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cz.kuhnt.moneta.assignment.feature.players.model.Team
import cz.kuhnt.moneta.assignment.feature.players.presentation.PlayerDetailViewModel
import cz.kuhnt.moneta.assignment.library.design.system.Dimensions
import cz.kuhnt.moneta.assignment.library.design.system.KeyValueRow
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
        onBack = viewModel::onBack,
        onTeamDetail = viewModel::onTeamDetail,
    )
}

@Composable
private fun Content(
    state: PlayerDetailViewModel.State,
    onBack: () -> Unit,
    onTeamDetail: (Team) -> Unit,
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
            KeyValueRow(stringResource(R.string.player_height), state.height)
            HorizontalDivider(modifier = Modifier.padding(horizontal = Dimensions.paddingM))
            KeyValueRow(stringResource(R.string.player_weight), state.weight)
            HorizontalDivider(modifier = Modifier.padding(horizontal = Dimensions.paddingM))
            KeyValueRow(stringResource(R.string.player_college), state.college)
            HorizontalDivider(modifier = Modifier.padding(horizontal = Dimensions.paddingM))
            KeyValueRow(stringResource(R.string.player_country), state.country)
            HorizontalDivider(modifier = Modifier.padding(horizontal = Dimensions.paddingM))
            KeyValueRow(stringResource(R.string.player_position), state.position)
            HorizontalDivider(modifier = Modifier.padding(horizontal = Dimensions.paddingM))
            state.team?.let { team ->
                KeyValueRow(stringResource(R.string.player_team), team.name)
                Button(
                    onClick = { onTeamDetail(team) },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(Dimensions.paddingM)
                ) {
                    Text(stringResource(R.string.player_team_detail))
                }
            }
        }
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
            team = Team(
                id = 1,
                name = "Warriors",
                conference = "East",
                division = "Pacific",
                city = "Oakland",
                fullName = "Oakland Warriors",
                abbreviation = "OKC"
            )
        ),
        onBack = {},
        onTeamDetail = {},
    )
}
