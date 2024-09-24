package cz.kuhnt.moneta.assignment.feature.players.presentation

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
        }
    }

    private fun viewModel() = PlayersViewModel(
        mockk(relaxed = true)
    )
}
