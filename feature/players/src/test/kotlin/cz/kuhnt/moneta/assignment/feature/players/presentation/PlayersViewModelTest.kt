package cz.kuhnt.moneta.assignment.feature.players.presentation

import cz.kuhnt.moneta.assignment.feature.players.domain.FetchPlayersUseCase
import cz.kuhnt.moneta.assignment.feature.players.domain.ObservePlayersUseCase
import cz.kuhnt.moneta.assignment.feature.players.model.Player
import cz.kuhnt.moneta.assignment.library.networking.data.Data
import cz.kuhnt.moneta.assignment.library.test.domain.returnsFlow
import cz.kuhnt.moneta.assignment.library.test.infrastructure.AbstractTest
import cz.kuhnt.moneta.assignment.library.usecase.domain.invoke
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

internal class PlayersViewModelTest: AbstractTest() {

    @Test
    fun `should correctly set state after creation`() {
        val viewModel = viewModel()

        assertSoftly(viewModel.states.value) {
            isLoading shouldBe false
            players shouldHaveSize 0
            error shouldBe null
        }
    }

    @Test
    fun `should observe players after creation`() {
        val players = listOf(
            mockk<Player>(),
            mockk<Player>()
        )
        val viewModel = viewModel(
            observePlayers = mockk { returnsFlow(players) }
        )

        viewModel.states.value.players shouldBe players
    }

    @Test
    fun `should fetch players after creation`() {
        val fetchPlayers: FetchPlayersUseCase = mockk(relaxed = true)
        viewModel(
            fetchPlayers = fetchPlayers
        )

        verify { fetchPlayers() }
    }

    @Test
    fun `should set loading when fetch players emit loading`() {
        val viewModel = viewModel(
            fetchPlayers = mockk { returnsFlow(Data.Loading) }
        )
        
        viewModel.states.value.isLoading shouldBe true
    }

    @Test
    fun `should hide loading when fetch players emit another data after loading`() {
        val viewModel = viewModel(
            fetchPlayers = mockk { returnsFlow(Data.Loading, Data.Error(Throwable())) }
        )
        
        viewModel.states.value.isLoading shouldBe false
    }

    @Test
    fun `should set error when fetch players emit error`() {
        val errorMessage = "No connection"
        val viewModel = viewModel(
            fetchPlayers = mockk { returnsFlow(Data.Error(Throwable(errorMessage))) }
        )

        viewModel.states.value.error shouldBe errorMessage
    }

    @Test
    fun `should clear error when error dialog dismissed`() {
        val viewModel = viewModel(
            fetchPlayers = mockk { returnsFlow(Data.Error(Throwable("Something went wrong"))) }
        )
        
        viewModel.onErrorDismiss()

        viewModel.states.value.error shouldBe null
    }

    private fun viewModel(
        fetchPlayers: FetchPlayersUseCase = mockk(relaxed = true),
        observePlayers: ObservePlayersUseCase = mockk(relaxed = true),
    ) = PlayersViewModel(
        fetchPlayers = fetchPlayers,
        observePlayers = observePlayers
    )
}
