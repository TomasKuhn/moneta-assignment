package cz.kuhnt.moneta.assignment.system

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cz.kuhnt.moneta.assignment.presentation.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun MainScreen() {
    val viewModel = koinViewModel<MainViewModel>()

    Content()
}

@Composable
private fun Content() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text(
                text = "Hello world!",
                modifier = Modifier
            )
        }
    }
}
