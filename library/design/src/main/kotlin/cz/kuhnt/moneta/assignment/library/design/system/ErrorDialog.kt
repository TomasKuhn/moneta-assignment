package cz.kuhnt.moneta.assignment.library.design.system

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cz.kuhnt.moneta.assignment.localization.R

@Composable
fun ErrorDialog(
    title: String,
    text: String,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(title) },
        text = { Text(text) },
        confirmButton = {
            TextButton(onClick = onDismiss) { Text(stringResource(R.string.common_ok)) }
        },
    )
}

@Preview
@Composable
fun ErrorDialogPreview() {
    ErrorDialog(
        title = "Dialog Title",
        text = "This is the dialog message.",
        onDismiss = {}
    )
}
