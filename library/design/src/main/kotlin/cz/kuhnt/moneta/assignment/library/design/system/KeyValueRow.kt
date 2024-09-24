package cz.kuhnt.moneta.assignment.library.design.system

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun KeyValueRow(key: String, value: String) {
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
