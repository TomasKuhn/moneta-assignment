package cz.kuhnt.moneta.assignment.feature.players.presentation

import cz.kuhnt.moneta.assignment.feature.players.domain.FetchPlayersUseCase
import cz.kuhnt.moneta.assignment.feature.players.model.Player
import cz.kuhnt.moneta.assignment.library.networking.data.Data
import cz.kuhnt.moneta.assignment.library.test.domain.returnsFlow
import cz.kuhnt.moneta.assignment.library.test.infrastructure.AbstractTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.mockk.mockk
import org.junit.Test

internal class PlayersViewModelTest: AbstractTest() {

    @Test
    fun `should correctly set state after creation`() {
        val viewModel = viewModel()

        assertSoftly(viewModel.states.value) {
            isLoading shouldBe false
            playerNames shouldHaveSize 0
            error shouldBe null
        }
    }

    @Test
    fun `should set loading when fetch players emit loading`() {
        val viewModel = viewModel(
            fetchPlayersUseCase = mockk { returnsFlow(Data.Loading) }
        )
        
        viewModel.states.value.isLoading shouldBe true
    }

    @Test
    fun `should hide loading when fetch players emit another data after loading`() {
        val viewModel = viewModel(
            fetchPlayersUseCase = mockk { returnsFlow(Data.Loading, Data.Error(Throwable())) }
        )
        
        viewModel.states.value.isLoading shouldBe false
    }

    @Test
    fun `should set error when fetch players emit error`() {
        val errorMessage = "No connection"
        val viewModel = viewModel(
            fetchPlayersUseCase = mockk { returnsFlow(Data.Error(Throwable(errorMessage))) }
        )

        viewModel.states.value.error shouldBe errorMessage
    }

    @Test
    fun `should set player names when fetch players emit success`() {
        val names = listOf("Stephen Curry", "John Mayer")
        val players = listOf(Player(names[0]), Player(names[1]))
        val viewModel = viewModel(
            fetchPlayersUseCase = mockk { returnsFlow(Data.Success(players)) }
        )

        viewModel.states.value.playerNames shouldBe names
    }

    private fun viewModel(
        fetchPlayersUseCase: FetchPlayersUseCase = mockk(relaxed = true)
    ) = PlayersViewModel(
        fetchPlayersUseCase = fetchPlayersUseCase,
    )
}
