package cz.kuhnt.moneta.assignment.feature.players.system

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cz.kuhnt.moneta.assignment.feature.players.presentation.TeamDetailViewModel
import cz.kuhnt.moneta.assignment.library.design.system.Dimensions
import cz.kuhnt.moneta.assignment.library.design.system.KeyValueRow
import cz.kuhnt.moneta.assignment.library.design.system.MonetaTheme
import cz.kuhnt.moneta.assignment.library.design.system.MonetaTopAppBar
import cz.kuhnt.moneta.assignment.localization.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun TeamDetailScreen() {
    val viewModel = koinViewModel<TeamDetailViewModel>()
    val state by viewModel.states.collectAsState()

    Content(
        state = state,
        onBack = viewModel::onBack
    )
}

@Composable
private fun Content(
    state: TeamDetailViewModel.State,
    onBack: () -> Unit
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
            KeyValueRow(stringResource(R.string.team_conference), state.conference)
            HorizontalDivider(modifier = Modifier.padding(horizontal = Dimensions.paddingM))
            KeyValueRow(stringResource(R.string.team_division), state.division)
            HorizontalDivider(modifier = Modifier.padding(horizontal = Dimensions.paddingM))
            KeyValueRow(stringResource(R.string.team_city), state.city)
            HorizontalDivider(modifier = Modifier.padding(horizontal = Dimensions.paddingM))
            KeyValueRow(stringResource(R.string.team_full_name), state.fullName)
            HorizontalDivider(modifier = Modifier.padding(horizontal = Dimensions.paddingM))
            KeyValueRow(stringResource(R.string.team_abbreviation), state.abbreviation)
            HorizontalDivider(modifier = Modifier.padding(horizontal = Dimensions.paddingM))
        }
    }
}

@Preview
@Composable
private fun TeamDetailPreview() = MonetaTheme {
    Content(
        state = TeamDetailViewModel.State(
            name = "Warriors",
            conference = "East",
            division = "Pacific",
            city = "Oakland",
            fullName = "Oakland Warriors",
            abbreviation = "OKC"
        ),
        onBack = {},
    )
}
